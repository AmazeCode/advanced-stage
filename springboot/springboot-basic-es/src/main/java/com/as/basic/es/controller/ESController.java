package com.as.basic.es.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.as.basic.es.util.EsUtils;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 视图层
 * @author: AmazeCode
 * @date: 2023/11/15 22:46
 */
@RestController
@RequestMapping("/es")
public class ESController {

    /**
     * @description: 查询集群状态
     * @param
     * @return: java.lang.String
     * @author: AmazeCode
     * @date: 2023/11/15 22:48
     */
    @GetMapping("/state")
    public String esState() throws IOException {
        Request request = new Request("GET","/_cluster/state");
        request.addParameter("pretty","true");
        Response response = EsUtils.init().performRequest(request);

        System.out.println(response.getStatusLine());
        return EntityUtils.toString(response.getEntity());
    }

    /**
     * @description: 添加记录
     * @param
     * @return: java.lang.String
     * @author: AmazeCode
     * @date: 2023/11/15 23:19
     */
    @PostMapping
    public String add() throws IOException {
        Request request = new Request("POST", "/itcast/_doc/7");
        Map<String, Object> data = new HashMap<>();
        data.put("name","基础");
        data.put("age","32");
        data.put("mail","888@qq.com");
        data.put("hobby","书法");
        request.setJsonEntity(JSONUtil.toJsonStr(data));
        Response response = EsUtils.init().performRequest(request);
        return EntityUtils.toString(response.getEntity());
    }

    /**
     * @description: 根据id查询
     * @param id 对应_id字段
     * @return: java.lang.String
     * @author: AmazeCode
     * @date: 2023/11/15 23:14
     */
    @GetMapping("/{id}")
    public String detail(@PathVariable("id") String id) throws IOException {
        Request request = new Request("GET", "/itcast/_doc/" + id);
        Response response = EsUtils.init().performRequest(request);
        System.out.println(response.getStatusLine());
        return EntityUtils.toString(response.getEntity());
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
        Request request = new Request("POST", "/itcast/_doc/_search");
        String searchJson = "{\"query\": {\"match\": {\"name\": \"王五\"}}}";
        request.setJsonEntity(searchJson);
        request.addParameter("pretty","true");
        Response response = EsUtils.init().performRequest(request);
        System.out.println(response.getStatusLine());
        return EntityUtils.toString(response.getEntity());
    }
}
