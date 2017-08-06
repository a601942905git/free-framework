package com.free.framework.util.http;

import com.free.framework.util.http.em.MediaTypeEnum;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

/**
 * com.free.framework.util.http.HttpUtils
 *
 * @author lipeng
 * @dateTime 2017/8/6 10:37
 */
@Slf4j
public class HttpUtils {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * @author lipeng
     * @description     发送get请求
     * @param url       请求的url地址
     * @return          响应数据
     * @dateTime 2017/8/6 12:27
     */
    public static String get(String url) {
        String responseStr;
        OkHttpClient okHttpClient = getOkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        responseStr = callAndResponse(okHttpClient, request, url);
        return responseStr;
    }

    /**
     * @author lipeng
     * @description     发送get请求
     * @param url       请求的url地址
     * @return          响应数据
     * @dateTime 2017/8/6 12:27
     */
    public static String getWithHeader(String url, Map<String, String> header) {
        String responseStr;
        OkHttpClient okHttpClient = getOkHttpClient();
        Request.Builder builder = new Request.Builder().url(url);
        Optional.ofNullable(header).ifPresent((headerMap) ->
                headerMap.forEach((key, value) -> builder.addHeader(key, value))
        );
        Request request = builder.build();
        responseStr = callAndResponse(okHttpClient, request, url);
        return responseStr;
    }

    /**
     * @author lipeng
     * @description
     * @param url               请求的url地址
     * @param requestBodyMap    请求的body参数 form表单格式
     * @return
     * @dateTime 2017/8/6 13:39
     */
    public static String postWithForm(String url, Map<String, String> requestBodyMap) {
        String responseStr = post(url, requestBodyMap, "", MediaTypeEnum.FORM.getId());
        return responseStr;
    }

    /**
     * @author lipeng
     * @description
     * @param url               请求的url地址
     * @param requestJson       请求的body参数 json格式
     * @return
     * @dateTime 2017/8/6 13:39
     */
    public static String postWithJson(String url, String requestJson) {
        String responseStr = post(url, null, requestJson, MediaTypeEnum.JSON.getId());
        return responseStr;
    }


    /**
     * @author lipeng
     * @description             公用post方法
     * @param url
     * @param requestBodyMap    post form参数
     * @param json              post json参数
     * @param type              post方式1.form 2.json
     * @return
     * @dateTime 2017/8/6 13:49
     */
    private static String post(String url, Map<String, String> requestBodyMap, String json, Integer type) {
        String responseStr;
        OkHttpClient okHttpClient = getOkHttpClient();
        Request.Builder requestBuilder = new Request.Builder().url(url);
        RequestBody requestBody = setRequestBody(requestBodyMap, json, type);
        Request request = requestBuilder.post(requestBody).build();
        responseStr = callAndResponse(okHttpClient, request, url);
        return responseStr;
    }

    /**
     * @author lipeng
     * @description             获取requestBody对象
     * @param requestBodyMap    post form参数
     * @param json              post json参数
     * @param type              post方式1.form 2.json
     * @return
     * @dateTime 2017/8/6 13:50
     */
    private static RequestBody setRequestBody(Map<String, String> requestBodyMap, String json, Integer type) {
        RequestBody requestBody = null;
        if (type == MediaTypeEnum.JSON.getId()) {
            requestBody = RequestBody.create(JSON, json);
        } else if (type == MediaTypeEnum.FORM.getId()) {
            FormBody.Builder formBodyBuilder = new FormBody.Builder();
            Optional.ofNullable(requestBodyMap).ifPresent((requestBodyMap1) ->
                    requestBodyMap1.forEach((key, value) -> formBodyBuilder.add(key, value))
            );
            requestBody = formBodyBuilder.build();
        }
        return requestBody;
    }

    /**
     * @author lipeng
     * @description
     * @param okHttpClient      okHttpClient对象
     * @param request           request对象
     * @return
     * @dateTime 2017/8/6 12:24
     */
    private static Call getCall(OkHttpClient okHttpClient,Request request) {
        Call call = okHttpClient.newCall(request);
        return call;
    }

    /**
     * @author lipeng
     * @description     获取okHttpClient对象
     * @param
     * @return          okHttpClient对象
     * @dateTime 2017/8/6 10:39
     */
    private static OkHttpClient getOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient();
        return okHttpClient;
    }

    /**
     * @author lipeng
     * @description     获取相应code码
     * @param response
     * @return
     * @dateTime 2017/8/6 12:51
     */
    private static int getResponseCode(Response response) {
        int responseCode = response.code();
        return responseCode;
    }

    /**
     * @author lipeng
     * @description
     * @param okHttpClient      okHttpClient对象
     * @param request           request对象
     * @param url               请求的url地址
     * @return
     * @dateTime 2017/8/6 12:56
     */
    private static String callAndResponse(OkHttpClient okHttpClient, Request request, String url) {
        String responseStr = "";
        Call call = getCall(okHttpClient, request);
        try {
            Response response = call.execute();
            responseStr = response.body().string();
            log.info("【请求:】{}响应内容:{}", url, getResponseCode(response));
        } catch (IOException e) {
            log.error("【HttpUtils中的get方法响应异常:】", e.fillInStackTrace());
        }
        return responseStr;
    }
}
