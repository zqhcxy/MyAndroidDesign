package com.github.zqhcxy.myandroiddesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
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

    private FloatingActionsMenu fab_menu;
    private FloatingActionButton fab_menu_acA;
    private FloatingActionButton fab_menu_acB;

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

        fab_menu = (FloatingActionsMenu) findViewById(R.id.fab_menu);
        fab_menu_acA = (FloatingActionButton) findViewById(R.id.fab_menu_acA);
        fab_menu_acB = (FloatingActionButton) findViewById(R.id.fab_menu_acB);
        fab_menu_acA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fab_menu.isExpanded()){
                    fab_menu.collapse();
                }
            }
        });
        fab_menu_acB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fab_menu.isExpanded()){//如果菜单展开，就关闭。
                    fab_menu.collapse();
                }
            }
        });

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
