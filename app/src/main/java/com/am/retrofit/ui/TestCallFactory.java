package com.am.retrofit.ui;

import com.am.retrofit.helper.CallFactory;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;

/**
 * 请求生成器
 * Created by Alex on 2019/1/29.
 */

public class TestCallFactory extends CallFactory<TestInterface> {
    private static TestCallFactory mInstance;

    private TestCallFactory() {
        super("http://t.weather.itboy.net", TestInterface.class);
    }

    private static TestCallFactory getInstance() {
        if (mInstance == null) {
            mInstance = new TestCallFactory();
        }
        return mInstance;
    }

    static Call<TestData> getWeather() {
        return getInstance().getInterface().getWeather();
    }

    @Override
    protected long getTimeoutSeconds() {
        return 15;
    }

    @Override
    protected HttpLoggingInterceptor.Level getLoggingLevel() {
        return HttpLoggingInterceptor.Level.BODY;
    }
}
