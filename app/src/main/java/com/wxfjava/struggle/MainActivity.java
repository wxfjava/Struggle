package com.wxfjava.struggle;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.wxfjava.struggle.utils.L;
import com.wxfjava.struggle.utils.ReflexUtils;

import java.io.File;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvInfo;

    static {
        System.loadLibrary("fh");
    }

    native String hello(String name);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            ClassLoader classLoader = getClassLoader();
            L.i(classLoader.getClass().getSimpleName());


            Object pathList = ReflexUtils.getProperty(classLoader, "pathList");
            L.i(pathList.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        tvInfo = findViewById(R.id.tv_info);
        tvInfo.setText(hello("Android"));

        File filesDir = this.getDir("odex", Context.MODE_PRIVATE);
        L.i("odex:" + filesDir.getAbsolutePath());

    }


}
