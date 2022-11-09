/*
 * Copyright (C) 2018 AlexMofer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.am.retrofit.helper;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 请求工厂
 * Created by Alex on 2018/3/14.
 */
public class CallFactory<I> {

    private final String mBaseUrl;
    private final Class<I> mServer;
    private I mInterface;

    public CallFactory(String baseUrl, Class<I> server) {
        mBaseUrl = baseUrl;
        mServer = server;
    }

    /**
     * 创建
     *
     * @param client   OkHttpClient构建器
     * @param retrofit Retrofit构建器
     * @return 接口
     */
    protected I onCreate(OkHttpClient.Builder client, Retrofit.Builder retrofit) {
        onInitializeOkHttpClientBuilder(client);
        onInitializeRetrofitBuilder(retrofit);
        return retrofit.client(client.build()).build().create(getInterfaceClass());
    }

    /**
     * 初始化OkHttpClient构造器
     *
     * @param builder OkHttpClient构造器
     */
    protected void onInitializeOkHttpClientBuilder(OkHttpClient.Builder builder) {
        final long timeout = getTimeoutSeconds();
        builder.writeTimeout(timeout, TimeUnit.SECONDS);
        builder.readTimeout(timeout, TimeUnit.SECONDS);
        builder.connectTimeout(timeout, TimeUnit.SECONDS);
        final HttpLoggingInterceptor.Level level = getLoggingLevel();
        if (level != HttpLoggingInterceptor.Level.NONE) {
            final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(level);
            builder.addInterceptor(interceptor);
        }
    }

    /**
     * 初始化RetrofitBuilder
     *
     * @param builder RetrofitBuilder
     */
    protected void onInitializeRetrofitBuilder(Retrofit.Builder builder) {
        builder.baseUrl(getBaseUrl());
        builder.addConverterFactory(getConverterFactory());
    }

    /**
     * 检查变化
     */
    protected void onCheckChange() {
    }

    /**
     * 获取通用超时时长
     *
     * @return 通用超时时长
     */
    protected long getTimeoutSeconds() {
        return 60000L;
    }

    /**
     * 获取Http日志等级
     *
     * @return Http日志等级
     */
    protected HttpLoggingInterceptor.Level getLoggingLevel() {
        return HttpLoggingInterceptor.Level.NONE;
    }

    /**
     * 获取转换工厂
     *
     * @return 转换工厂
     */
    protected Converter.Factory getConverterFactory() {
        return GsonConverterFactory.create();
    }

    /**
     * 获取基础链接
     *
     * @return 基础链接
     */
    protected String getBaseUrl() {
        return mBaseUrl;
    }

    /**
     * 获取接口类名
     *
     * @return 类名
     */
    protected Class<I> getInterfaceClass() {
        return mServer;
    }

    /**
     * 刷新接口
     */
    public void invalidateInterface() {
        mInterface = null;
    }

    /**
     * 获取接口
     *
     * @return 接口
     */
    public I getInterface() {
        onCheckChange();
        if (mInterface == null) {
            final OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
            final Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
            mInterface = onCreate(clientBuilder, retrofitBuilder);
        }
        return mInterface;
    }
}
