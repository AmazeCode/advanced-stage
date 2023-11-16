package com.ac.adwanced.es.controller;

import cn.hutool.json.JSONUtil;
import com.ac.adwanced.es.util.EsUtils;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Response;
import org.elasticsearch.core.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @description: 高级客户端视图层
 * @author: AmazeCode
 * @date: 2023/11/15 22:46
 */
@RestController
@RequestMapping("/es")
public class ESController {

    /**
     * @description: 新增文档,同步操作
     * @param
     * @return: java.lang.String
     * @author: AmazeCode
     * @date: 2023/11/15 23:19
     */
    @PostMapping("/sync")
    public String add() throws IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("name","高级");
        data.put("age","32");
        data.put("mail","999@qq.com");
        data.put("hobby","书法 跳舞");

        IndexRequest indexRequest = new IndexRequest().index("itcast").id("8");
        indexRequest.source(data);
        IndexResponse indexResponse = EsUtils.init().index(indexRequest, RequestOptions.DEFAULT);
        indexResponse.getResult();
        return indexResponse.getResult().toString();
    }

    /**
     * @description: 新增文档,异步操作
     * @param
     * @return: java.lang.String
     * @author: AmazeCode
     * @date: 2023/11/15 23:19
     */
    @PostMapping("/async")
    public String addAsync() {
        Map<String, Object> data = new HashMap<>();
        data.put("name","异步新增文档");
        data.put("age","32");
        data.put("mail","999@qq.com");
        data.put("hobby","书法 跳舞");

        IndexRequest indexRequest = new IndexRequest().index("itcast").id("9");
        indexRequest.source(data);
        // 异步新增文档
        EsUtils.init().indexAsync(indexRequest, RequestOptions.DEFAULT, new ActionListener<IndexResponse>() {
            @Override
            public void onResponse(IndexResponse indexResponse) {
                System.out.println("id->" + indexResponse.getId());
                System.out.println("index->" + indexResponse.getIndex());
                System.out.println("type->" + indexResponse.getType());
                System.out.println("version->" + indexResponse.getVersion());
                System.out.println("result->" + indexResponse.getResult());
                System.out.println("shardInfo->" + indexResponse.getShardInfo());
            }

            @Override
            public void onFailure(Exception e) {
                System.out.println(e);
            }
        });
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return "ok";
    }

    /**
     * @description: 查询
     * @param id 对应_id字段
     * @return: java.lang.String
     * @author: AmazeCode
     * @date: 2023/11/15 23:14
     */
    @GetMapping("/{id}")
    public String query(@PathVariable("id") String id) throws IOException {

        GetRequest getRequest = new GetRequest("itcast").id(id);
        GetResponse getResponse = EsUtils.init().get(getRequest, RequestOptions.DEFAULT);
        return getResponse.getSourceAsString();
    }

    /**
     * @description: 判断是否存在
     * @return: java.lang.String
     * @author: AmazeCode
     * @date: 2023/11/15 23:14
     */
    @GetMapping("/exist")
    public String exist() throws IOException {

        GetRequest getRequest = new GetRequest("itcast").id("test");
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        boolean exists = EsUtils.init().exists(getRequest, RequestOptions.DEFAULT);
        return String.valueOf(exists);
    }

    /**
     * @description: 删除
     * @param
     * @return: java.lang.String
     * @author: AmazeCode
     * @date: 2023/11/16 21:28
     */
    @DeleteMapping()
    public String delete() throws IOException {

        DeleteRequest deleteRequest = new DeleteRequest("itcast").id("9");
        DeleteResponse response = EsUtils.init().delete(deleteRequest, RequestOptions.DEFAULT);
        return String.valueOf(response.status());
    }

    /**
     * @description: 修改
     * @param
     * @return: java.lang.String
     * @author: AmazeCode
     * @date: 2023/11/16 21:28
     */
    @PutMapping("/{id}")
    public String update(@PathVariable("id") String id) throws IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("name","修改");
        data.put("age","33");
        data.put("mail","999@qq.com");
        data.put("hobby","书法 跳舞");

        UpdateRequest updateRequest = new UpdateRequest("itcast",id);
        updateRequest.doc(data);
        UpdateResponse response = EsUtils.init().update(updateRequest, RequestOptions.DEFAULT);
        return String.valueOf(response.status());
    }

    /**
     * @description: 条件搜索
     * @param
     * @return: java.lang.String
     * @author: AmazeCode
     * @date: 2023/11/15 23:19
     */
    @GetMapping("/search")
    public String search() throws IOException {
        SearchRequest searchRequest = new SearchRequest("itcast");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("name","王五"));
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(5);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        // 添加高亮查询
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<font color='red' >");//添加前缀
        highlightBuilder.postTags("</font>");//添加后缀
        highlightBuilder.field("name");//高亮标签内容
        searchSourceBuilder.highlighter(highlightBuilder);

        searchRequest.source(searchSourceBuilder);
        SearchResponse search = EsUtils.init().search(searchRequest, RequestOptions.DEFAULT);
        System.out.println("搜索到 " + search.getHits().getTotalHits() + " 条数据");
        SearchHits hits = search.getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }

        return JSONUtil.toJsonStr(hits);
    }
}
