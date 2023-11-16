package com.ac.adwanced.es.util;

import org.apache.http.HttpHost;
import org.elasticsearch.client.Node;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * @description: ES工具类
 * @author: AmazeCode
 * @date: 2023/11/15 22:39
 */
public class EsUtils {

    public static RestHighLevelClient init() {
        // 连接es服务
        RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost("192.168.0.103",9200,"http"));
        restClientBuilder.setFailureListener(new RestClient.FailureListener(){
            @Override
            public void onFailure(Node node) {
                System.out.println("出错了 -> " + node);
            }
        });
        // 构建客户端
        return new RestHighLevelClient(restClientBuilder);
    }
}
