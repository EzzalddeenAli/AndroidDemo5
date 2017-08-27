package com.example.lzl.a50demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBt= (Button) findViewById(R.id.ripple);
        mBt.setText(mBt.getLeft()+"=="+mBt.getRight());

    }
}
