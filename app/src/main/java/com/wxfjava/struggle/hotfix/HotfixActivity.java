package com.wxfjava.struggle.hotfix;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wxfjava.struggle.R;

public class HotfixActivity extends AppCompatActivity {

    private EnginService enginService = new EnginService();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotfix);
    }


    public void badButton(View view) {

        enginService.showMsg(this);
    }

    public void fixButton(View view) {
    }
}
