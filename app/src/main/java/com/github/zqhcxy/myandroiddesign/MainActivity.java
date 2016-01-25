package com.github.zqhcxy.myandroiddesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.zqhcxy.myandroiddesign.activity.FABRevealLayoutActivity;
import com.github.zqhcxy.myandroiddesign.adapter.MyRecycleViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 一些关于开源的Android Design的控件实现
 *
 * @author zqhcxy
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView rcv_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    /**
     * 初始化视图控件
     */
    private void initView() {
        rcv_view = (RecyclerView) findViewById(R.id.rcv_view);

    }

    /**
     * 初始化数据
     */
    private void initData() {

        rcv_view.setLayoutManager(new LinearLayoutManager(this));//默认垂直分布
        rcv_view.setHasFixedSize(true);
        List<String> tableList = new ArrayList<>();
        tableList.add("FABRevealLayout");
        MyRecycleViewAdapter myRecycleViewAdapter = new MyRecycleViewAdapter(tableList);
        rcv_view.setAdapter(myRecycleViewAdapter);
        myRecycleViewAdapter.setItemClickLitener(new OnItemClickLitener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent();
                switch (position) {
                    case 0:
                        intent.setClass(MainActivity.this, FABRevealLayoutActivity.class);
                        break;
                }
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View v, int position) {

            }
        });
    }


    public interface OnItemClickLitener {
        void onItemClick(View v, int position);

        void onItemLongClick(View v, int position);
    }
}
