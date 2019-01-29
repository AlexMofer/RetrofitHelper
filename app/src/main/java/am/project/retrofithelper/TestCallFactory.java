package am.project.retrofithelper;

import am.project.retrofithelper.gson.GsonConverterFactory;
import am.util.retrofit.CallFactory;
import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * 请求生成器
 * Created by Xiang Zhicheng on 2019/1/29.
 */

public class TestCallFactory extends CallFactory<TestService> {
    private static TestCallFactory mInstance;

    private TestCallFactory() {
        createService(TestService.class);
    }

    @Override
    protected void onInitializeRetrofitBuilder(Retrofit.Builder builder) {
        super.onInitializeRetrofitBuilder(builder);
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.baseUrl("https://www.sojson.com/open/");
    }

    @Override
    public long getTimeout() {
        return 15000L;
    }

    @Override
    public boolean isLogging() {
        return BuildConfig.DEBUG;
    }

    private static TestCallFactory getInstance() {
        if (mInstance == null) {
            mInstance = new TestCallFactory();
        }
        return mInstance;
    }

     static Call<TestBean> getWeather(@SuppressWarnings("SameParameterValue") String city) {
        return getInstance().getService().getWeather(city);
    }
}
