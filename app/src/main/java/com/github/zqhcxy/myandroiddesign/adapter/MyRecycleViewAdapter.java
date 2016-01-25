package com.github.zqhcxy.myandroiddesign.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.zqhcxy.myandroiddesign.MainActivity;
import com.github.zqhcxy.myandroiddesign.R;

import java.util.List;

/**
 * Created by zqh-pc on 2016/1/25.
 */


public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.ViewHolder> {
    private List<String> titleList;
    MainActivity.OnItemClickLitener itemClickLitener;

    public MyRecycleViewAdapter(List<String> titles) {
        titleList = titles;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recycleview_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tv_titleName.setText(titleList.get(position));
        holder.tv_titleName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickLitener != null) {
                    itemClickLitener.onItemClick(v, position);
                }
            }
        });
        holder.tv_titleName.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (itemClickLitener != null) {
                    itemClickLitener.onItemLongClick(v, position);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return titleList.size();
    }

    public void setItemClickLitener(MainActivity.OnItemClickLitener itemClickLitener) {
        this.itemClickLitener = itemClickLitener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_titleName;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_titleName = (TextView) itemView.findViewById(R.id.tv_titleName);
        }
    }
}
