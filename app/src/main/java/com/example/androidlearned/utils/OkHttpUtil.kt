package com.example.androidlearned.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpUtil {

    private static final OkHttpClient client = new OkHttpClient();

    // 同步GET请求
    public static String get(String url) throws Exception {
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    // 异步GET请求
    public static void getAsync(String url, okhttp3.Callback callback) {
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(callback);
    }

    // 同步POST请求
    public static String post(String url, String json) throws Exception {
        RequestBody body = RequestBody.create(okhttp3.MediaType.get("application/json"),json);
        Request request = new Request.Builder().url(url).post(body).build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    // 异步POST请求
    public static void postAsync(String url, String json, okhttp3.Callback callback) {
        RequestBody body = RequestBody.create(okhttp3.MediaType.get("application/json"),json);
        Request request = new Request.Builder().url(url).post(body).build();
        client.newCall(request).enqueue(callback);
    }
}
