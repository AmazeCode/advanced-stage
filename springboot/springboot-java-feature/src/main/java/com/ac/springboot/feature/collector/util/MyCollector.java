package com.ac.springboot.feature.collector.util;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.*;
import java.util.stream.Collector;

/**
 * 自定义Collector
 * 实现功能 计算流中每个元素的某个int字段值平方的总和
 * @Author: zhangyadong
 * @Date: 2022/8/10
 * @Version: v1.0
 */
public class MyCollector<T> implements Collector<T, AtomicInteger, Integer> {

    private ToIntFunction<T> mapper;

    public MyCollector(ToIntFunction<T> mapper) {
        this.mapper = mapper;
    }

    /**
     * toList()源码方法分析：
     * supplier方法：ArrayList::new，即new了个ArrayList作为结果存储容器。
     * accumulator方法：List::add，也就是对于stream中的每个元素，都调用list.add()方法添加到结果容器追踪。
     * combiner方法：(left, right) -> { left.addAll(right); return left; }，也就是对于并行操作生成的各个子ArrayList结果，最终通过list.addAll()方法合并为最终结果。
     * finisher方法：没提供，使用的默认的，因为无需做任何处理，属于恒等操作。
     * characteristics：返回的是IDENTITY_FINISH，也即最终结果直接返回，无需finisher方法去二次加工。注意这里没有声明CONCURRENT，因为ArrayList是个非线程安全的容器，所以这个收集器是不支持在并发过程中使用。
     */

    /**
     * 1.2 创建新的结果容器，可以是一个容器，也可以是一个累加器实例，总之是用来存储结果数据的
     * @return
     */
    @Override
    public Supplier<AtomicInteger> supplier() {
        // 指定用于最终结果的收集，此处返回new AtomicInteger(0)，后续在此基础上累加
        return () -> new AtomicInteger(0);
    }

    /**
     * 1.1 元素进入收集器中的具体处理操作
     * @return
     */
    @Override
    public BiConsumer<AtomicInteger,T> accumulator() {
        // 每个元素进入的遍历策略,当前元素值的平方与sum结果进行累加
        return (sum, current) -> {
            int intValue = mapper.applyAsInt(current);
            sum.addAndGet(intValue * intValue);
        };
    }

    /**
     * 2 各个子流的处理结果最终如何合并到一起去，比如并行流处理场景，元素会被切分为好多个分片进行并行处理，
     * 最终各个分片的数据需要合并为一个整体结果，即通过此方法来指定子结果的合并逻辑
     * @return
     */
    @Override
    public BinaryOperator<AtomicInteger> combiner() {
        // 多个分段结果处理的策略，直接相加
        return (sum1, sum2) -> {
            sum1.addAndGet(sum2.get());
            return sum1;
        };
    }

    /**
     * 3 当所有元素都处理完成后，在返回结果前的对结果的最终处理操作，当然也可以选择不做任何处理，直接返回
     * @return
     */
    @Override
    public Function<AtomicInteger,Integer> finisher() {
        // 结果处理完成之后对结果的二次处理
        // 为了支持多线程并发处理，此处内部使用了AtomicInteger作为了结果累加器
        // 但是收集器最终需要返回Integer类型值，此处进行对结果的转换
        return AtomicInteger::get;
    }

    /**
     * 对此收集器处理行为的补充描述，比如此收集器是否允许并行流中处理，是否finisher方法必须要有等等，
     * 此处返回一个Set集合，里面的候选值是固定的几个可选项。
     *
     * 对于characteristics返回set集合中的可选值:
     * UNORDERED	声明此收集器的汇总归约结果与Stream流元素遍历顺序无关，不受元素处理顺序影响
     * CONCURRENT	声明此收集器可以多个线程并行处理，允许并行流中进行处理
     * IDENTITY_FINISH	声明此收集器的finisher方法是一个恒等操作，可以跳过
     * @return
     */
    @Override
    public Set<Characteristics> characteristics() {
        Set<Characteristics> characteristics = new HashSet<>();
        // 指定该收集器支持并发处理（前面也发现我们采用了线程安全的AtomicInteger方式）
        characteristics.add(Characteristics.CONCURRENT);
        // 声明元素数据处理的先后顺序不影响最终收集的结果
        characteristics.add(Characteristics.UNORDERED);
        // 注意:这里没有添加下面这句，因为finisher方法对结果进行了处理，非恒等转换
        // characteristics.add(Characteristics.IDENTITY_FINISH);
        return characteristics;
    }
}
