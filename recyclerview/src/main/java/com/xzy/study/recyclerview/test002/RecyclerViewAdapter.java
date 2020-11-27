package com.xzy.study.recyclerview.test002;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xzy.study.recyclerview.R;

import java.util.Collections;
import java.util.List;


/**
 * 删除位置更新参考了 https://blog.csdn.net/bigmoster/article/details/77161327
 * @author ：created by xzy.
 * @date ：2020/11/25
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private Context context;
    private OnChildListener listener;
    private RecyclerView recyclerView;
    private final List<String> list;

    public void setListener(OnChildListener listener) {
        this.listener = listener;
    }

    public RecyclerViewAdapter(Context context, List<String> list) {
        this.list = list;
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Item2ViewHolder(mLayoutInflater.inflate(R.layout.item_layout_02, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof Item2ViewHolder) {
            ((Item2ViewHolder) holder).mTextView.setText(list.get(position));
            ((Item2ViewHolder) holder).mImageAddView.setOnClickListener(v -> listener.onImageClick(position, v));
            ((Item2ViewHolder) holder).mImageDelView.setOnClickListener(v -> listener.onImageClick(position, v));
            ((Item2ViewHolder) holder).mImageUpdateView.setOnClickListener(v -> listener.onImageClick(position, v));
            ((Item2ViewHolder) holder).itemView.setOnClickListener(v ->
                    listener.onChildClick(recyclerView, ((Item2ViewHolder) holder).itemView, position, list.get(position)));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * item2 的 ViewHolder
     */
    public static class Item2ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        ImageView mImageDelView;
        ImageView mImageAddView;
        ImageView mImageUpdateView;

        public Item2ViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tv_text);
            mImageAddView = itemView.findViewById(R.id.iv_add_item);
            mImageDelView = itemView.findViewById(R.id.iv_delete_item);
            mImageUpdateView = itemView.findViewById(R.id.iv_change_item);
        }
    }


    public void add(int position, String data) {
        list.add(position, data);
        notifyItemInserted(position);
        recyclerView.scrollToPosition(position);
        // 加入如下代码保证 position 的位置正确性
        notifyItemRangeChanged(0, list.size());
    }

    public void remove(int position) {
        list.remove(position);
        notifyItemRemoved(position);
        // 加入如下代码保证 position 的位置正确性
        notifyItemRangeChanged(0, list.size());
    }

    public void change(int position, String data) {
        list.remove(position);
        list.add(position, data);
        notifyItemChanged(position);
    }

    /**
     * 当连接到RecyclerView后，提供数据的时候调用这个方法
     *
     * @param recyclerView rv 控件
     */
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    /**
     * 解绑
     */
    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.recyclerView = null;
    }

    /**
     * item 以及 item 内部的点击事件
     */
    public interface OnChildListener {

        /**
         * @param parent   recyclerView
         * @param view     被点击的 view
         * @param position position
         * @param data     传递的数据
         */
        void onChildClick(RecyclerView parent, View view, int position, String data);

        /**
         * @param position position
         */
        void onImageClick(int position, View view);
    }
}