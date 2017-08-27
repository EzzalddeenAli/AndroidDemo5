package com.example.lzl.recyclerview;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzl on 16/9/13.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CustomViewHolder> {
    private static final String TAG = RecyclerAdapter.class.getName();
    private List<String> mData;
    private Context mContext;
    private CallBack mCallBack;
    private List<DataBean> mCheckedState;
    private ViewAnimator mViewAnimator;
    private RecyclerView recyclerView;

    public RecyclerAdapter(List<String> data, Context context, CallBack callBack, RecyclerView recyclerView) {
        mData = data;
        mContext = context;
        mCallBack = callBack;
        initChecked();
        mViewAnimator = new ViewAnimator(recyclerView);
        this.recyclerView = recyclerView;
    }

    private void initChecked() {
        mCheckedState = new ArrayList<>();
        for (int i = 0; i < mData.size(); i++) {
            DataBean dataBean = new DataBean();
            dataBean.setChecked(false);
            mCheckedState.add(i, dataBean);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e(TAG, "creatViewHolder=======");
        View item = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        CheckBox checkBox = (CheckBox) item.findViewById(R.id.checkbox);

        final CustomViewHolder viewHolder = new CustomViewHolder(item);
        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.onItemClick(viewHolder.getAdapterPosition(), viewHolder.getLayoutPosition());
            }
        });
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mCheckedState.get(viewHolder.getAdapterPosition()).setChecked(true);
                } else {
                    mCheckedState.get(viewHolder.getAdapterPosition()).setChecked(false);
                }
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Log.e(TAG, "ViewHolder对象==" + holder.toString());
        Log.e(TAG, "onBindViewHolder==position==" + position + "绑定值==" + mData.get(position));

        holder.textView.setText(mData.get(position));
        if (mCheckedState.get(position).isChecked()) {
            holder.checkBox.setChecked(true);
        } else {
            holder.checkBox.setChecked(false);
        }
        mViewAnimator.cancelExistingAnimation(holder.itemView);
        animateView(holder.itemView, position);
    }

    private void animateView(final View view, final int position) {
        assert mViewAnimator != null;
        assert recyclerView != null;
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0.6f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 0.6f, 1f);
        Animator[] scaleAnimator= new ObjectAnimator[]{scaleX, scaleY};
        Animator[] animators = new Animator[0];
        Animator alphaAnimator = ObjectAnimator.ofFloat(view, "alpha", 0, 1);
        Animator[] concatAnimators = mViewAnimator.concatAnimators(scaleAnimator, alphaAnimator);
        mViewAnimator.setViewAnimator(position, view, concatAnimators);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CheckBox checkBox;

        public CustomViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);

        }
    }

    public static interface CallBack {
        void onItemClick(int adapterP, int layoutP);
    }


}
