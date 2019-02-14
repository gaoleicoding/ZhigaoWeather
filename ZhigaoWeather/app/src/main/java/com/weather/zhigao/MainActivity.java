package com.weather.zhigao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Map<String, String> params = new HashMap<>();
        params.put("location", "CN101010300");
        params.put("key", "0dc8f1d3269642dda46421a87767cb84");
        OkhttpUtil.getInstance().getDataAsync(Urls.url_weather_forecast, params, new ResponseCallBack() {
            @Override
            public void onFailure(String error) {
                textView.setText(error);
            }

            @Override
            public void onResponse(String response) {
                textView.setText(response);
            }
        });
    }
}
