package com.wxfjava.struggle;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.wxfjava.struggle.utils.L;
import com.wxfjava.struggle.utils.ReflexUtils;

public class MainActivity extends AppCompatActivity {

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


    }
}
