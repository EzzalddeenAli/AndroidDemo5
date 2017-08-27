package com.example.lzl.testpath;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by lzl on 16/6/2.
 */
public class MyPathMeasure extends View {

    private Path p;
    private PathMeasure pathMeasure;

    public MyPathMeasure(Context context) {
        this(context, null);
        // TODO Auto-generated constructor stub
    }

    public MyPathMeasure(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setDither(true);
        mPaint.setColor(0xff0000ff);
        mPaint.setStyle(Paint.Style.STROKE);

    }

    private void init() {
        p = new Path();
        mPaint.setStyle(Paint.Style.STROKE);
        p.moveTo(0, getHeight() / 2);
        p.cubicTo(getWidth() / 2 - 60, getHeight() / 2 - 260,
                getWidth() / 2 + 60, getHeight() / 2 + 260, getWidth()-20,
                getHeight() / 2);
        pathMeasure = new PathMeasure(p, false);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // TODO Auto-generated method stub
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d("TAG","w=="+w+"h=="+h+"oldw=="+oldw+"oldh=="+oldh);
        init();
    }

    public MyPathMeasure(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    private Paint mPaint;
    private float[] floats = new float[2];
    private boolean first = true;

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        Log.d("TAG","onDraw===");
        mPaint.setColor(0xffff0000);
        canvas.drawPath(p, mPaint);
        mPaint.setColor(0xff00ff00);
        canvas.drawCircle(floats[0], floats[1], 10, mPaint);

        if (first) {
            first = false;
            startAnimation();
        }

    }

    void startAnimation() {
        ValueAnimator animation = ValueAnimator.ofFloat(0,
                pathMeasure.getLength());
        animation.setDuration(4000);

        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (Float) animation.getAnimatedValue();
                pathMeasure.getPosTan(v, floats, null);
                postInvalidate();
            }
        });

        animation.start();

    }
}

