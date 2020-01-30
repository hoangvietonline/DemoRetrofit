package com.hoangviet.demoui.master;


import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hoang viet android 30/01/1996.
 */

public class MasterAdapter extends RecyclerView.Adapter<MasterAdapter.ViewHolder> {
    private List<String> mData;
    private OnClickItemListener onClickItemListener;
    public MasterAdapter() {
        mData = new ArrayList<>();
    }

    public void addData(List<String> data) {
        if (data == null) {
            return;
        }
        mData.addAll(data);
        notifyDataSetChanged();
    }
    public void setOnClickItemListener(OnClickItemListener onClickItemListener){
        this.onClickItemListener = onClickItemListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(mData.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItemListener.onClickItem(mData.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(android.R.id.text1);

        }

        public void bindData(String name) {
            if (TextUtils.isEmpty(name)) {
                return;
            }
            mTextView.setText(name);
        }
    }
    interface OnClickItemListener{
        void onClickItem(String item);
    }
}
