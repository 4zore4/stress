package com.var.stress.service;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.var.stress.domain.nomad.jobs.Jobs;
import okhttp3.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HttpService {


    OkHttpClient httpClient = new OkHttpClient();
    private MediaType BODY_ENCODE = MediaType.parse("application/json; charset=utf-8");
    private static final boolean SUCCESS = true;

    public String doGetRequest(Map<String,String> params,String url) throws Exception {
        String paramsStr = encodeParams(params);
        String res = request("GET", url,null);

//        List<Jobs> jobs = JSON.parseArray(res, Jobs.class);
//        return strToObj(res,url,paramsStr);
        return res;
    }

    public Object doRequest(String method, String params, String url) throws Exception {
        RequestBody body = null;

        if (params != null) {
            body = RequestBody.create(BODY_ENCODE, params);
        }
        String res = request(method, url, body);
        return res;
    }

    private String request(String method, String url, RequestBody body) throws IOException {
        Request request;
        request = new Request.Builder()
                .addHeader("Accept","application/json")
                .url(new URL(url))
                .method(method, body)
                .build();

        Response response = httpClient.newCall(request).execute();

        return response.body() != null ? response.body().string() : null;
    }

    private List<Jobs> strToObj(String res, String url, String params) throws Exception {
        Map<String, Object> response;
        if (res != null){
            List<Jobs> lists =  JSON.parseObject(res,List.class);
            return lists;
        }
        throw new IOException();
    }

    private String mapToStr(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    private String encodeParams(Map<String,String> params){
        List<Map.Entry<String,String>> paramsList = new ArrayList<>(params.entrySet());
        for (String key : params.keySet()){
            params.put(key, URLEncoder.encode(params.put(key,"UTF-8")));
        }
        return StringUtils.collectionToDelimitedString(
                paramsList.stream().map(e -> String.format("%s=%s", e.getKey(), e.getValue())).collect(Collectors.toList()), "&"
        );
    }

}
