package com.wxfjava.struggle.hotfix;

import android.content.Context;
import android.widget.Toast;

public class EnginService {


    public void showMsg(Context context) {
        Toast.makeText(context, "我是一个坏按钮", Toast.LENGTH_LONG).show();
    }

}
