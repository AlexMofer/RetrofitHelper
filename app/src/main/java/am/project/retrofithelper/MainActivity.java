package am.project.retrofithelper;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import am.project.retrofithelper.gson.GsonHelper;
import am.util.retrofit.TinyCallback;
import am.util.retrofit.WeakCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements TinyCallback<TestBean> {

    private EditText mVInput;
    private TextView mVOutput;
    private final TestCallback mCallback = new TestCallback(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVInput = findViewById(R.id.main_edt_input);
        mVOutput = findViewById(R.id.main_tv_output);
    }

    public void test(View view) {
        switch (view.getId()) {
            case R.id.main_btn_test:
                final InputMethodManager imm =
                        (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null)
                    imm.hideSoftInputFromWindow(mVInput.getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                String input = mVInput.getText().toString().trim();
                if (TextUtils.isEmpty(input))
                    input = getString(R.string.main_input_hint);
                TestCallFactory.getWeather(input).enqueue(new WeakCallback<>(mCallback));
                break;
        }
    }

    @Override
    public void onResponse(TestBean result) {
        if (result == null)
            mVOutput.setText(R.string.main_output_null);
        else
            mVOutput.setText(GsonHelper.toJson(result));
    }

    @Override
    public void onFailure(int code, String message, TestBean result) {
        mVOutput.setText(getString(R.string.main_output_error, code, message,
                (result == null ? "null" : GsonHelper.toJson(result))));
    }
}
