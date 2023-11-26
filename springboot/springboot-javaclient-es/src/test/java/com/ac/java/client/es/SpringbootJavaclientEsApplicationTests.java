package com.ac.java.client.es;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.*;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.JsonData;
import com.ac.java.client.es.config.EsConfig;
import com.ac.java.client.es.pojo.HotelDoc;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class SpringbootJavaclientEsApplicationTests {

    /*
        es 7.15 开始使用Api client 此例子是简单查询例子,该api统一简化操作，封装对象便于链式编程
     */
    @Resource
    private EsConfig esConfig;

    /**
     * @description: 查询所有文档
     * @param
     * @return: void
     * @author: AmazeCode
     * @date: 2023/11/26 11:21
     */
    @Test
    void matchAll() throws IOException {
        SearchRequest request = SearchRequest.of(searRequest -> searRequest
                .index("hotel")
                .query(QueryBuilders.matchAll().build()._toQuery())
        );

        SearchResponse<HotelDoc> search = esConfig.esClient().search(request, HotelDoc.class);
        List<HotelDoc> hotelDocs = search.hits().hits().stream().map(Hit::source).collect(Collectors.toList());
        hotelDocs.forEach(hotelDoc -> System.out.println(hotelDoc.toString()));
    }

    /**
     * @description: 查询匹配
     * @param
     * @return: void
     * @author: AmazeCode
     * @date: 2023/11/26 11:50
     */
    @Test
    void match() throws IOException {
        // 1、构建查询条件
        SearchRequest request = SearchRequest.of(searRequest -> searRequest
                .index("hotel")
                .query(q -> q.multiMatch(m -> m))
        );

        // 2、执行查询
        SearchResponse<HotelDoc> search = esConfig.esClient().search(request, HotelDoc.class);
        List<HotelDoc> hotelDocs = search.hits().hits().stream().map(Hit::source).collect(Collectors.toList());
        hotelDocs.forEach(hotelDoc -> System.out.println(hotelDoc.toString()));
    }

    @Test
    void bool() throws IOException {
        Query byBrand = MatchQuery.of(m -> m
                        .field("brand")
                        .query("如家")
                )
                //MatchQuery是一个查询变体，我们必须将其转换为 Query 联合类型
                ._toQuery();
        Query byPrice = RangeQuery.of(m -> m
                        .field("price")
                        // Elasticsearch 范围查询接受大范围的值类型。我们在这里创建最高价格的 JSON 表示。
                        .gte(JsonData.of(145)))
                ._toQuery();

        SearchRequest request = SearchRequest.of(s -> s.index("hotel")
                .query(q -> q.bool(b ->
                    b.must(byPrice).must(byBrand)
                )));

        // 2、执行查询
        SearchResponse<HotelDoc> search = esConfig.esClient().search(request, HotelDoc.class);
        List<HotelDoc> hotelDocs = search.hits().hits().stream().map(Hit::source).collect(Collectors.toList());
        hotelDocs.forEach(hotelDoc -> System.out.println(hotelDoc.toString()));
    }
}
