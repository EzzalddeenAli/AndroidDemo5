package com.example.lzl.a40demo;

import android.app.Application;
import android.content.Intent;

/**
 * Created by lzl on 16/8/24.
 */
public class MyApplication extends Application {

    public void test(){
        Intent intent=new Intent(this,Main2Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
