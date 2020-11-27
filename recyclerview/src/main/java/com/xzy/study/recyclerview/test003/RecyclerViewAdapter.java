package com.xzy.study.recyclerview.test003;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.xzy.study.recyclerview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：created by xzy.
 * @date ：2020/11/27
 */
@SuppressWarnings("AlibabaAvoidCommentBehindStatement")
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    /**
     * 数据源.
     */
    private List<String> stringList;
    private final Context context;

    /**
     * 第一种ViewType，正常的item.
     */
    private final int normalType = 0;

    /**
     * 第二种ViewType，底部的提示View.
     */
    private final int footType = 1;

    /**
     * 是否有更多数据.
     */
    private boolean hasMore;
    /**
     * 是否隐藏了底部的提示.
     */
    private boolean fadeTips = false;

    /**
     * 每一页大小.
     */
    private int PAGE_COUNT = 20;

    /**
     * 获取主线程的Handler.
     */
    private final Handler mHandler = new Handler(Looper.getMainLooper());


    public RecyclerViewAdapter(List<String> stringList, Context context, boolean hasMore) {
        // 初始化变量
        this.stringList = stringList;
        this.context = context;
        this.hasMore = hasMore;
    }


    /**
     * 获取条目数量，之所以要加1是因为增加了一条footView
     *
     * @return 算上 footView 之后的总数
     */
    @Override
    public int getItemCount() {
        return stringList.size() + 1;
    }

    /**
     * 自定义方法，获取列表中数据源的最后一个位置，比getItemCount少1，因为不计上footView
     *
     * @return 去掉 footView 之后的总数
     */
    public int getRealLastPosition() {
        return stringList.size();
    }

    /**
     * 根据条目位置返回ViewType，以供onCreateViewHolder方法内获取不同的Holder
     *
     * @param position 当前位置
     * @return ViewType
     */
    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return footType;
        } else {
            return normalType;
        }
    }


    /**
     * 正常item的ViewHolder，用以缓存findView操作
     */
    static class NormalHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public NormalHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
        }
    }


    /**
     * 底部footView的ViewHolder，用以缓存findView操作
     */
    static class FootHolder extends RecyclerView.ViewHolder {
        private final TextView tips;
        private final ProgressBar progressBar;

        public FootHolder(View itemView) {
            super(itemView);
            tips = itemView.findViewById(R.id.item_footer_message);
            progressBar = itemView.findViewById(R.id.item_footer_progress);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 根据返回的ViewType，绑定不同的布局文件，这里只有两种
        if (viewType == normalType) {
            return new NormalHolder(LayoutInflater.from(context).inflate(R.layout.item_custom, parent, false));
        } else {
            return new FootHolder(LayoutInflater.from(context).inflate(R.layout.item_layout_footer, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        // 如果是正常的 item，直接设置TextView的值
        if (holder instanceof NormalHolder) {
            ((NormalHolder) holder).textView.setText(stringList.get(position));
        } else {
            // 之所以要设置可见，是因为我在没有更多数据时会隐藏了这个footView
            ((FootHolder) holder).tips.setVisibility(View.VISIBLE);
            if (getRealLastPosition() < PAGE_COUNT) {
                ((FootHolder) holder).progressBar.setVisibility(View.GONE);
                ((FootHolder) holder).tips.setText("没有更多数据了");
                return;
            }
            // 只有获取数据为空时，hasMore为false，所以当我们拉到底部时基本都会首先显示“正在加载更多...”
            if (hasMore) {
                // 不隐藏footView提示
                fadeTips = false;
                // 如果查询数据发现增加之后，就显示正在加载更多
                ((FootHolder) holder).tips.setVisibility(View.VISIBLE);
                ((FootHolder) holder).progressBar.setVisibility(View.VISIBLE);
                ((FootHolder) holder).tips.setText("正在加载更多...");
            } else {
                if (stringList.size() > 0) {
                    // 如果查询数据发现并没有增加时，就显示没有更多数据了
                    ((FootHolder) holder).tips.setVisibility(View.VISIBLE);
                    ((FootHolder) holder).progressBar.setVisibility(View.GONE);
                    ((FootHolder) holder).tips.setText("没有更多数据了");

                    // 然后通过延时加载模拟网络请求的时间，在500ms后执行
                    mHandler.postDelayed(() -> {
                        // 隐藏提示条
                        ((FootHolder) holder).progressBar.setVisibility(View.GONE);
                        // 将fadeTips设置true
                        fadeTips = true;
                        // hasMore设为true是为了让再次拉到底时，会先显示正在加载更多
                        hasMore = true;
                    }, 500);
                }
            }
        }
    }

    // 暴露接口，改变fadeTips的方法
    public boolean isFadeTips() {
        return fadeTips;
    }

    // 暴露接口，下拉刷新时，通过暴露方法将数据源置为空
    public void resetDatas() {
        stringList = new ArrayList<>();
    }

    // 暴露接口，更新数据源，并修改hasMore的值，如果有增加数据，hasMore为true，否则为false
    public void updateList(List<String> newDatas, boolean hasMore) {
        // 在原有的数据之上增加新数据
        if (newDatas != null) {
            stringList.addAll(newDatas);
        }
        this.hasMore = hasMore;
        notifyDataSetChanged();
    }
}
