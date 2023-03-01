package com.am.retrofit.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.am.appcompat.app.AppCompatActivity;
import com.am.retrofit.helper.Callback;
import com.am.retrofit.helper.CallbackWrapper;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;

public class MainActivity extends AppCompatActivity implements Callback<TestData> {

    private final CallbackWrapper<TestData> mCallback =
            new CallbackWrapper<TestData>().setCallback(this, true);
    private TextView mVOutput;

    public MainActivity() {
        super(R.layout.activity_main);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mVOutput = findViewById(R.id.main_tv_output);
        findViewById(R.id.main_btn_test).setOnClickListener(v ->
                TestCallFactory.getWeather().enqueue(mCallback));
    }

    @Override
    public void onResponse(TestData result) {
        if (result == null)
            mVOutput.setText(R.string.retrofit_output_null);
        else
            mVOutput.setText(new GsonBuilder()
                    .setDateFormat(DateFormat.LONG)
                    .create()
                    .toJson(result, TestData.class));
    }

    @Override
    public void onFailure(int code, String message) {
        mVOutput.setText(getString(R.string.retrofit_output_error, code, message, ("null")));
    }
}