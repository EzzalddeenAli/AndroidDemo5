package com.example.lzl.getwidth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView,mTextView1,mTextView2,mTextView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.test);
        mTextView1 = (TextView) findViewById(R.id.test1);
        mTextView2 = (TextView) findViewById(R.id.test2);
        mTextView3 = (TextView) findViewById(R.id.test3);
        mTextView.setText("onCreate＝＝＝＝width===" + mTextView.getWidth() + "height==" + mTextView.getHeight() + "\n" + "measureW==" + mTextView.getMeasuredWidth() + "measureH==" + mTextView.getMeasuredHeight());
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTextView1.setText("onResume＝＝＝＝width===" + mTextView.getWidth() + "height==" + mTextView.getHeight() + "\n" + "measureW==" + mTextView.getMeasuredWidth() + "measureH==" + mTextView.getMeasuredHeight());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mTextView2.setText("onStart＝＝＝＝width===" + mTextView.getWidth() + "height==" + mTextView.getHeight() + "\n" + "measureW==" + mTextView.getMeasuredWidth() + "measureH==" + mTextView.getMeasuredHeight());
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            mTextView3.setText("onWindowFocusChanged＝＝＝＝width===" + mTextView.getWidth() + "height==" + mTextView.getHeight() + "\n" + "measureW==" + mTextView.getMeasuredWidth() + "measureH==" + mTextView.getMeasuredHeight());
        }

    }
}
