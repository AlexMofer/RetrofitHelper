
package com.am.retrofit.ui;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * 测试接口
 * Created by Alex on 2019/1/29.
 */
interface TestInterface {

    /**
     * 获取天气
     *
     * @return 请求
     */
    @GET("api/weather/city/101030100")
    Call<TestData> getWeather();
}
