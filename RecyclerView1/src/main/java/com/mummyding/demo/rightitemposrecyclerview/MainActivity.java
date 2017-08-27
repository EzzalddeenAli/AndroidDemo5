package com.mummyding.demo.rightitemposrecyclerview;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Adapter.ClickCall {

    private RecyclerView recyclerView;
    private Adapter adapter;
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        bt= (Button) findViewById(R.id.bt_test);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addItemDecoration(new ItemDivider(this,R.drawable.s));
        adapter = new Adapter(this, 100,this);
        recyclerView.setAdapter(adapter);
        ViewTreeObserver child=bt.getViewTreeObserver();
        child.addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
            @Override
            public void onGlobalFocusChanged(View oldFocus, View newFocus) {

               LinearLayout.LayoutParams ll= (LinearLayout.LayoutParams) bt.getLayoutParams();
                int b=ll.bottomMargin;
                int t=ll.topMargin;
            }
        });

    }

    @Override
    public void onItemClick(int position,int adapterPositon) {
        Toast.makeText(this,"getLayoutPosition()"+position+"====adapterPositon"+adapterPositon,Toast.LENGTH_LONG).show();

    }

    public class ItemDivider extends RecyclerView.ItemDecoration {

        private Drawable mDrawable;

        public ItemDivider(Context context, int resId) {
            //在这里我们传入作为Divider的Drawable对象
            mDrawable = context.getResources().getDrawable(resId);
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent) {
            final int left = parent.getPaddingLeft();
            final int right = parent.getWidth() - parent.getPaddingRight();

            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                //以下计算主要用来确定绘制的位置
                new Handler().postDelayed(new Runnable(){

                    @Override
                    public void run() {
                        int bottomMargin=params.bottomMargin;
                    }
                },1000);

                final int top = child.getBottom() + params.bottomMargin;
                final int bottom = top + mDrawable.getIntrinsicHeight();
                mDrawable.setBounds(left, top, right, bottom);
                mDrawable.draw(c);
            }
        }

        @Override
        public void getItemOffsets(Rect outRect, int position, RecyclerView parent) {
            outRect.set(0, 0, 0, mDrawable.getIntrinsicWidth());
        }
    }


    public class MyItemAnimator extends RecyclerView.ItemAnimator {

        List<RecyclerView.ViewHolder> mAnimationAddViewHolders = new ArrayList<RecyclerView.ViewHolder>();
        List<RecyclerView.ViewHolder> mAnimationRemoveViewHolders = new ArrayList<RecyclerView.ViewHolder>();

        @Override
        public boolean animateDisappearance(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull ItemHolderInfo preLayoutInfo, @Nullable ItemHolderInfo postLayoutInfo) {
            return false;
        }

        @Override
        public boolean animateAppearance(@NonNull RecyclerView.ViewHolder viewHolder, @Nullable ItemHolderInfo preLayoutInfo, @NonNull ItemHolderInfo postLayoutInfo) {
            return false;
        }

        @Override
        public boolean animatePersistence(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull ItemHolderInfo preLayoutInfo, @NonNull ItemHolderInfo postLayoutInfo) {
            return false;
        }

        @Override
        public boolean animateChange(@NonNull RecyclerView.ViewHolder oldHolder, @NonNull RecyclerView.ViewHolder newHolder, @NonNull ItemHolderInfo preLayoutInfo, @NonNull ItemHolderInfo postLayoutInfo) {
            return false;
        }

        //需要执行动画时会系统会调用，用户无需手动调用
        @Override
        public void runPendingAnimations() {
            if (!mAnimationAddViewHolders.isEmpty()) {

                AnimatorSet animator;
                View target;
                for (final RecyclerView.ViewHolder viewHolder : mAnimationAddViewHolders) {
                    target = viewHolder.itemView;
                    animator = new AnimatorSet();

                    animator.playTogether(
                            ObjectAnimator.ofFloat(target, "translationX", -target.getMeasuredWidth(), 0.0f),
                            ObjectAnimator.ofFloat(target, "alpha", target.getAlpha(), 1.0f)
                    );

                    animator.setTarget(target);
                    animator.setDuration(100);
                    animator.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            mAnimationAddViewHolders.remove(viewHolder);
                            if (!isRunning()) {
                                dispatchAnimationsFinished();
                            }
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
                    animator.start();
                }
            }
            else if(!mAnimationRemoveViewHolders.isEmpty()){
            }
        }
        //remove时系统会调用，返回值表示是否需要执行动画
//        @Override
//        public boolean animateRemove(RecyclerView.ViewHolder viewHolder) {
//            return mAnimationRemoveViewHolders.add(viewHolder);
//        }
//
//        //viewholder添加时系统会调用
//        @Override
//        public boolean animateAdd(RecyclerView.ViewHolder viewHolder) {
//            return mAnimationAddViewHolders.add(viewHolder);
//        }
//
//        @Override
//        public boolean animateMove(RecyclerView.ViewHolder viewHolder, int i, int i2, int i3, int i4) {
//            return false;
//        }

        @Override
        public void endAnimation(RecyclerView.ViewHolder viewHolder) {
        }

        @Override
        public void endAnimations() {
        }

        @Override
        public boolean isRunning() {
            return !(mAnimationAddViewHolders.isEmpty()&&mAnimationRemoveViewHolders.isEmpty());
        }

    }
}
