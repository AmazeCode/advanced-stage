package com.ac.java.client.es.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @description: Es相关配置
 * @author: AmazeCode
 * @date: 2023/11/26 10:24
 */
@Component
public class EsConfig {

    @Value("${elasticsearch.clusterNodes}")
    private String clusterNodes;

    @Value("${elasticsearch.account}")
    private String account;

    @Value("${elasticsearch.passWord}")
    private String passWord;

    public ElasticsearchClient client;

    /**
     * @description: 集群配置
     * <p>
     *     es 7.15 开始标记 HighLevelRestClient Http rest连接方式将被淘汰，后续提供了Java Api Client的连接方式
     *     , 该配置是使用Java Client 进行配置例子
     * </p>
     * @param
     * @return: void
     * @author: AmazeCode
     * @date: 2023/11/26 10:25
     */
    public ElasticsearchClient esClient(){
        HttpHost[] httpHosts = Arrays.stream(clusterNodes.split(",")).map(x -> {
            String[] hostInfo = x.split(":");
            return new HttpHost(hostInfo[0], Integer.parseInt(hostInfo[1]));
        }).toArray(HttpHost[]::new);

        RestClientBuilder builder = RestClient.builder(httpHosts);

        /*
        // 设置账号操作
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(account, passWord));//设置账号密码

        RestClientBuilder builder = RestClient.builder(httpHosts)
                .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));*/

        // 1、创建低级客户端
        RestClient restClient = builder.build();
        // 2、创建transport的JacksonMapper映射
        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());
        // 3、创建Api客户端
        client = new ElasticsearchClient(transport);
        return client;
    }
}
