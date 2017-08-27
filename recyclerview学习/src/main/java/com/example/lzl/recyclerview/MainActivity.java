package com.example.lzl.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.CallBack {
    private RecyclerView mRecycler;
    private RecyclerAdapter mAdapter;
    private  List<String> mName;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecycler = (RecyclerView) findViewById(R.id.recyclerView);
        init();


    }

    private void init() {
         mName = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mName.add(i + "");
        }
        mAdapter = new RecyclerAdapter(mName, this, this,mRecycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycler.setLayoutManager(layoutManager);
        mRecycler.setAdapter(mAdapter);
        mAdapter.registerAdapterDataObserver(new DataObserver());
    }


    public void removeItem(View view) {
        Toast.makeText(this,"test"+mAdapter.getItemCount(),Toast.LENGTH_SHORT).show();
        mName.remove(0);
        Toast.makeText(this,"删除后"+mAdapter.getItemCount(),Toast.LENGTH_LONG).show();
//        mAdapter.notifyDataSetChanged();
        mAdapter.notifyItemRemoved(0);
    }

    @Override
    public void onItemClick(int adapterP, int layoutP) {
        Toast.makeText(MainActivity.this, "getAdapterPosition=="+adapterP+"getLayoutPosition=="+layoutP, Toast.LENGTH_SHORT).show();
        mName.set(adapterP,adapterP+"n");
//        mAdapter.bindViewHolder(getViewHolder(),adapterP);
        mAdapter.notifyItemChanged(adapterP);

    }

    private RecyclerAdapter.CustomViewHolder getViewHolder(){
        View item= LayoutInflater.from(this).inflate(R.layout.item,mRecycler,false);
       TextView tv= (TextView) item.findViewById(R.id.tv);
        tv.setText("TEST");
        CheckBox checkBox= (CheckBox) item.findViewById(R.id.checkbox);
        checkBox.setChecked(true);

        RecyclerAdapter.CustomViewHolder customViewHolder=new RecyclerAdapter.CustomViewHolder(item);
        return customViewHolder;
    }

    public  class DataObserver extends RecyclerView.AdapterDataObserver{
        @Override
        public void onChanged() {
            super.onChanged();
            Toast.makeText(MainActivity.this,"observer",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            super.onItemRangeChanged(positionStart, itemCount);
            Toast.makeText(MainActivity.this,"onItemRangeChanged==positionStart=="+positionStart+"itemCount=="+itemCount,Toast.LENGTH_LONG).show();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            super.onItemRangeChanged(positionStart, itemCount, payload);
            Toast.makeText(MainActivity.this,"onItemRangeChangedpayload==positionStart=="+positionStart+"itemCount=="+itemCount,Toast.LENGTH_LONG).show();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            super.onItemRangeInserted(positionStart, itemCount);
            Toast.makeText(MainActivity.this,"onItemRangeInserted==positionStart=="+positionStart+"itemCount=="+itemCount,Toast.LENGTH_LONG).show();
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            super.onItemRangeMoved(fromPosition, toPosition, itemCount);
            Toast.makeText(MainActivity.this,"onItemRangeMoved==positionStart=="+fromPosition+"itemCount=="+itemCount,Toast.LENGTH_LONG).show();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            super.onItemRangeRemoved(positionStart, itemCount);

        }
    }
}
