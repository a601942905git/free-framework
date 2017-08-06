package com.free.framework.util.http;

import com.free.framework.util.http.em.MediaTypeEnum;
import com.free.framework.util.http.em.RequestTypeEnum;
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
     * @description     发送同步get请求
     * @param url       请求的url地址
     * @return          响应数据
     * @dateTime 2017/8/6 12:27
     */
    public static String get(String url) {
        String responseStr = getCommon(url, null, RequestTypeEnum.SYNC);
        return responseStr;
    }

    /**
     * @author lipeng
     * @description     发送异步get请求
     * @param url       请求的url地址
     * @return          响应数据
     * @dateTime 2017/8/6 12:27
     */
    public static String getAsync(String url) {
        String responseStr = getCommon(url, null, RequestTypeEnum.ASYNC);
        return responseStr;
    }

    /**
     * @author lipeng
     * @description     发送同步get请求
     * @param url       请求的url地址
     * @return          响应数据
     * @dateTime 2017/8/6 12:27
     */
    public static String getWithHeader(String url, Map<String, String> header) {
        String responseStr = getCommon(url, header, RequestTypeEnum.SYNC);
        return responseStr;
    }

    /**
     * @author lipeng
     * @description     发送异步get请求
     * @param url       请求的url地址
     * @return          响应数据
     * @dateTime 2017/8/6 12:27
     */
    public static String getWithHeaderAsync(String url, Map<String, String> header) {
        String responseStr = getCommon(url, header, RequestTypeEnum.ASYNC);
        return responseStr;
    }

    /**
     * @author lipeng
     * @description             get请求公用
     * @param url               请求url地址
     * @param requestTypeEnum   请求类型同步or异步
     * @return
     * @dateTime 2017/8/6 14:47
     */
    private static String getCommon(String url,  Map<String, String> header ,RequestTypeEnum requestTypeEnum) {
        String responseStr;
        OkHttpClient okHttpClient = getOkHttpClient();
        Request.Builder builder = new Request.Builder().url(url);
        Optional.ofNullable(header).ifPresent((headerMap) ->
                headerMap.forEach((key, value) -> builder.addHeader(key, value))
        );
        Request request = builder.build();
        responseStr = asyncOrNot(okHttpClient, request, url, requestTypeEnum);
        return responseStr;
    }

    /**
     * @author lipeng
     * @description             同步form请求
     * @param url               请求的url地址
     * @param requestBodyMap    请求的body参数 form表单格式
     * @return
     * @dateTime 2017/8/6 13:39
     */
    public static String postWithForm(String url, Map<String, String> requestBodyMap) {
        String responseStr = post(url, requestBodyMap, "", MediaTypeEnum.FORM, RequestTypeEnum.ASYNC);
        return responseStr;
    }

    /**
     * @author lipeng
     * @description             异步form请求
     * @param url               请求的url地址
     * @param requestBodyMap    请求的body参数 form表单格式
     * @return
     * @dateTime 2017/8/6 13:39
     */
    public static String postWithFormAsync(String url, Map<String, String> requestBodyMap) {
        String responseStr = post(url, requestBodyMap, "", MediaTypeEnum.FORM, RequestTypeEnum.ASYNC);
        return responseStr;
    }

    /**
     * @author lipeng
     * @description             同步json请求
     * @param url               请求的url地址
     * @param requestJson       请求的body参数 json格式
     * @return
     * @dateTime 2017/8/6 13:39
     */
    public static String postWithJson(String url, String requestJson) {
        String responseStr = post(url, null, requestJson, MediaTypeEnum.JSON, RequestTypeEnum.SYNC);
        return responseStr;
    }

    /**
     * @author lipeng
     * @description             异步json请求
     * @param url               请求的url地址
     * @param requestJson       请求的body参数 json格式
     * @return
     * @dateTime 2017/8/6 13:39
     */
    public static String postWithJsonAsync(String url, String requestJson) {
        String responseStr = post(url, null, requestJson, MediaTypeEnum.JSON, RequestTypeEnum.ASYNC);
        return responseStr;
    }


    /**
     * @author lipeng
     * @description             公用post方法
     * @param url
     * @param requestBodyMap    post form参数
     * @param json              post json参数
     * @param mediaTypeEnum     post方式:FORM普通表单 JSON json格式
     * @return
     * @dateTime 2017/8/6 13:49
     */
    private static String post(String url, Map<String, String> requestBodyMap, String json, MediaTypeEnum mediaTypeEnum, RequestTypeEnum requestTypeEnum) {
        String responseStr;
        OkHttpClient okHttpClient = getOkHttpClient();
        Request.Builder requestBuilder = new Request.Builder().url(url);
        RequestBody requestBody = setRequestBody(requestBodyMap, json, mediaTypeEnum);
        Request request = requestBuilder.post(requestBody).build();
        responseStr = asyncOrNot(okHttpClient, request, url, requestTypeEnum);
        return responseStr;
    }

    /**
     * @author lipeng
     * @description             获取requestBody对象
     * @param requestBodyMap    post form参数
     * @param json              post json参数
     * @param mediaTypeEnum     post方式:FORM普通表单 JSON json格式
     * @return
     * @dateTime 2017/8/6 13:50
     */
    private static RequestBody setRequestBody(Map<String, String> requestBodyMap, String json, MediaTypeEnum mediaTypeEnum) {
        RequestBody requestBody = null;
        if (mediaTypeEnum == MediaTypeEnum.JSON) {
            requestBody = RequestBody.create(JSON, json);
        } else if (mediaTypeEnum == MediaTypeEnum.FORM) {
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
     * @param okHttpClient          okHttpClient对象
     * @param request               request对象
     * @param url                   请求的url地址
     * @param requestTypeEnum       请求类型:同步或者异步
     * @return
     * @dateTime 2017/8/6 15:13
     */
    private static String asyncOrNot(OkHttpClient okHttpClient, Request request, String url, RequestTypeEnum requestTypeEnum) {
        String responseStr;
        if (requestTypeEnum == RequestTypeEnum.SYNC) {
            responseStr = callAndResponse(okHttpClient, request, url);
        } else {
            responseStr = callAndResponseAsync(okHttpClient, request, url);
        }
        return responseStr;
    }

    /**
     * @author lipeng
     * @description             同步发起请求并获取响应数据
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

    /**
     * @author lipeng
     * @description             异步发起请求并获取响应数据
     * @param okHttpClient      okHttpClient对象
     * @param request           request对象
     * @param url               请求的url地址
     * @return
     * @dateTime 2017/8/6 12:56
     */
    private static String callAndResponseAsync(OkHttpClient okHttpClient, Request request, String url) {
        final String[] responseStr = new String[1];
        Call call = getCall(okHttpClient, request);
        try {
            call.execute();
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    log.error("【HttpUtils中url:{}响应异常:】", url, e.fillInStackTrace());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    responseStr[0] = response.body().string();
                    log.info("【请求:】{}响应内容:{}", url, getResponseCode(response));
                }
            });
        } catch (IOException e) {
            log.error("【HttpUtils中请求url:{}响应异常:】", url, e.fillInStackTrace());
        }
        return responseStr[0];
    }
}
