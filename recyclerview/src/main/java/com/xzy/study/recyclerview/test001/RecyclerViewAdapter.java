package com.xzy.study.recyclerview.test001;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xzy.study.recyclerview.R;


/**
 * @author ：created by xzy.
 * @date ：2020/11/25
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private Context context;
    private String[] titles;
    private OnChildListener listener;
    private RecyclerView recyclerView;

    /**
     * 建立枚举 2个item 类型.
     */
    public enum ITEM_TYPE {
        /**
         * 布局类型一.
         */
        ITEM1,
        /**
         * 布局类型二.
         */
        ITEM2
    }

    public void setListener(OnChildListener listener) {
        this.listener = listener;
    }

    public RecyclerViewAdapter(Context context, String[] titles) {
        this.titles = titles;
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 加载 Item View 的时候根据不同 TYPE 加载不同的布局
        if (viewType == ITEM_TYPE.ITEM1.ordinal()) {
            return new Item1ViewHolder(mLayoutInflater.inflate(R.layout.item_layout_01_001, parent, false));
        } else {
            return new Item2ViewHolder(mLayoutInflater.inflate(R.layout.item_layout_01_002, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof Item1ViewHolder) {
            ((Item1ViewHolder) holder).mTextView.setText(titles[position]);
            ((Item1ViewHolder) holder).itemView.setOnClickListener(v ->
                    listener.onChildClick(recyclerView, ((Item1ViewHolder) holder).itemView, position, titles[position]));
        } else if (holder instanceof Item2ViewHolder) {
            ((Item2ViewHolder) holder).mTextView.setText(titles[position]);
            ((Item2ViewHolder) holder).mImageView.setOnClickListener(v -> listener.onImageClick(position));
            ((Item2ViewHolder) holder).itemView.setOnClickListener(v ->
                    listener.onChildClick(recyclerView, ((Item2ViewHolder) holder).itemView, position, titles[position]));
        }
    }


    /**
     * 设置 ITEM 类型，可以自由发挥，这里设置 item position 单数显示 item1 偶数显示 item2
     *
     * @param position item 对应的位置
     * @return itemType
     */
    @Override
    public int getItemViewType(int position) {
        // Enum 类提供了一个 ordinal() 方法，返回枚举类型的序数，
        // 这里 ITEM_TYPE.ITEM1.ordinal() 代表 0，ITEM_TYPE.ITEM2.ordinal() 代表 1
        return position % 2 == 0 ? ITEM_TYPE.ITEM1.ordinal() : ITEM_TYPE.ITEM2.ordinal();
    }

    @Override
    public int getItemCount() {
        return titles == null ? 0 : titles.length;
    }

    /**
     * item1 的 ViewHolder
     */
    public static class Item1ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        public Item1ViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tv_text);
        }
    }


    /**
     * item2 的 ViewHolder
     */
    public static class Item2ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        ImageView mImageView;

        public Item2ViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tv_text);
            mImageView = itemView.findViewById(R.id.iv_img);
        }
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
        void onImageClick(int position);
    }
}