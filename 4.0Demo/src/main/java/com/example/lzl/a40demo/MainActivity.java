package com.example.lzl.a40demo;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView mTv01, mTv02, mTv03,mTv04,mTv05;
    private LinearLayout mLinearLayout;
    private TranslateAnimation mShowAction;
    private MyApplication myApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv01 = (TextView) findViewById(R.id.test_01);
        mTv02 = (TextView) findViewById(R.id.test_02);
        mTv03 = (TextView) findViewById(R.id.test_03);
        mTv04= (TextView) findViewById(R.id.test_04);
        mTv05= (TextView) findViewById(R.id.test_05);
        mLinearLayout = (LinearLayout) findViewById(R.id.test_ll);

   myApplication=(MyApplication) getApplication();
    }

    public void startAnimation(View view){
        myApplication.test();
        mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mShowAction.setDuration(2000);
        ImageView imageView = (ImageView)findViewById(R.id.iv_01);


        mShowAction.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mLinearLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mLinearLayout.setVisibility(View.VISIBLE);
        mLinearLayout.startAnimation(mShowAction);

//        ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(mTv04,"scaleX",0.0f,2.0f);
//        objectAnimator.setDuration(3000);
//        objectAnimator.start();
//        mTv04.setVisibility(View.VISIBLE);
//        LayoutTransition layoutTransition=new LayoutTransition();
//        layoutTransition.setDuration(2000);
//        layoutTransition.setAnimator(LayoutTransition.APPEARING,objectAnimator);
//        mLinearLayout.setLayoutTransition(layoutTransition);

    }
public void onTest(View view){


}
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {

        }
        DisplayMetrics display = getResources().getDisplayMetrics();
        mTv03.setText("屏幕宽==" + display.widthPixels + "屏幕高==" + display.heightPixels + "密度==" + display.densityDpi + "==" + display.density);
        mTv02.setText("视图width==" + mTv01.getWidth() + "====视图height===" + mTv01.getHeight());
        Rect rect=new Rect();
        mTv05.getLocalVisibleRect(rect);
        mTv05.setText("getWidth=="+mTv05.getWidth()+"getHeight=="+mTv05.getHeight()+"left=="+rect.left+"top=="+rect.top+"right=="+rect.right+"bottom==="+rect.bottom);
        mTv01.setText("getLeft==" + mTv01.getLeft() + "===getRight===" + mTv01.getRight() + "===getTop===" + mTv01.getTop() + "getBottom===" + mTv01.getBottom() + "===" + "translationx==" + mTv01.getTranslationX() + "===getX==" + mTv01.getX() + "===" + "getY==" + mTv01.getY());
       /*
         * ViewGroup中使用LayoutTransition 进行 监听布局的改变，而创建动画
		 * LayoutTransition.APPEARING 新增出现时
		 *                  CHANGE_APPEARING
		 *                  CHANGE_DISAPPEARING 子view消失时
		 *                  CHANGING
		 * ViewGroup布局中：android:animateLayoutChanges="true"  使用的默认的LayoutTransition制作动画
		 */
//        ObjectAnimator objectAnimator= (ObjectAnimator) AnimatorInflater.loadAnimator(this,R.animator.scale);
//        LayoutTransition layoutTransition = new LayoutTransition();
//        layoutTransition.setDuration(5000);
//        layoutTransition.setAnimator(LayoutTransition.APPEARING, objectAnimator);
//        mLinearLayout.setLayoutTransition(layoutTransition);
//
//        final TextView tv = new TextView(this);
//        tv.setWidth(100);
//        tv.setHeight(100);
//        tv.setText("中华人民共和国");
//        mLinearLayout.addView(tv);//对应type = APPEARING
//
//        mLinearLayout.postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
////                mLinearLayout.removeView(tv);
//            }
//        }, 2000);
    }
}
