package com.xzy.study.recyclerview.test003.refresh;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Scroller;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xzy.study.recyclerview.test003.CustomItemDecoration;

/**
 * @author xzy
 */
public class PullToRefreshRecycleView extends LinearLayout {

    //头部
    private IHeaderView mHeaderView;
    private int mHeaderHeight;

    //尾部
    private CustomFooterView mFooterView;
    private int mFooterHeight;

    //阻尼系数,越大，阻力越大
    public static final float RATIO = 0.5f;
    //当前是否阻止事件
    private boolean isIntercept;
    //是否正在刷新
    private boolean isRefreshing;
    //滑动类
    private Scroller mScroller;
    //刷新的View
    private RecyclerView mRefreshView;

    private Context mContext;

    private int mMaxScrollHeight;

    public boolean isFirst = true;

    public static final int NORMAL = 0;
    public static final int PULL_TO_REFRESH = 1;
    public static final int RELEASE_TO_REFRESH = 2;
    public static final int REFRESING = 3;
    private int mCurrentState;
    private int mTouchSlop;

    private OnRefreshListener listener;

    private boolean isPullDownMotion;
    private int lastVisible;

    public PullToRefreshRecycleView(Context context) {
        super(context);
        init(context);
    }

    public PullToRefreshRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    private void init(Context context) {
        mContext = context;
        this.setOrientation(VERTICAL);
        mRefreshView = getRefreshView();
        mRefreshView.setBackgroundColor(Color.WHITE);
        LayoutParams listParams = new LayoutParams(-1, -1);
        mRefreshView.setLayoutParams(listParams);
        addView(mRefreshView);
        //添加HeaderView
        mHeaderView = new CustomHeaderView(context);
        LayoutParams params = new LayoutParams(-1, -2);
        mHeaderView.setLayoutParams(params);
        addView(mHeaderView, 0);
        //添加FooterView
        mFooterView = new CustomFooterView(context);
        LayoutParams fParams = new LayoutParams(-1, 200);
        mFooterView.setLayoutParams(fParams);
        addView(mFooterView, -1);
        //弹性滑动实现
        mScroller = new Scroller(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //第一次获取相关参数，并隐藏HeaderView，FooterView
        if (isFirst) {
            mHeaderHeight = mHeaderView.getMeasuredHeight();
            mMaxScrollHeight = mHeaderHeight * 3;
            resetHeaderLayout(-mHeaderHeight);

            mFooterHeight = mFooterView.getMeasuredHeight();
            resetFooterLayout(-mFooterHeight);
            Log.v("@mHeaderHeight", mHeaderHeight + "");
            Log.v("@mFooterHeight", mFooterHeight + "");
            isFirst = false;
        }
    }

    private void resetHeaderLayout(int offset) {
        LayoutParams params = (LayoutParams) mHeaderView.getLayoutParams();
        params.topMargin = offset;
        mHeaderView.requestLayout();
    }

    private void resetFooterLayout(int offset) {
        LayoutParams params = (LayoutParams) mFooterView.getLayoutParams();
        params.bottomMargin = offset;
        mFooterView.requestLayout();

    }

    //按下时的位置,当事件被阻止时，第一次ActionDown事件，onTouchEvent无法获取这个位置
    //需要在onInterceptTouchEvent获取
    private float downY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //如果当前是正在刷新并且是下拉状态，则当前视图处理事件
        if (isRefreshing && mScrollY < 0) {
            return true;
        }
        //如果当前是刷新状态，并且处于上拉状态，则视图不可进入下拉状态
        if (mScrollY >= 0 && isRefreshing) {
            return false;
        }
        boolean isIntercept = false;
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:

                //如果达到了滑动条件
                if (Math.abs(ev.getY() - downY) >= mTouchSlop) {
                    if (ev.getY() - downY > 0) {//下拉
                        isIntercept = isEnablePullDown();
                        if (isIntercept)//设置下拉还是上滑的状态,true表示下拉动作
                        {
                            isPullDownMotion = true;
                        }

                    } else {//上滑
                        isIntercept = isEnableLoadMore();
                        if (isIntercept)//false表示上滑状态
                        {
                            isPullDownMotion = false;
                        }
                    }
                } else {
                    isIntercept = false;
                }

                break;
            case MotionEvent.ACTION_CANCEL:
                //如果返回true，子视图如果包含点击事件，则无法进行处理
                isIntercept = false;
                break;
            case MotionEvent.ACTION_UP:
                isIntercept = false;
                break;
            default:
                // throw new IllegalStateException("Unexpected value: " + action);
                break;
        }
        return isIntercept;
    }

    //记录当前滑动的位置
    private int mScrollY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                //第一次判断时，downY只能从intercept中获取，之后从这里获取
                downY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float dY = event.getY() - downY;
                if (isPullDownMotion)//下拉
                {
                    doPullDownMoveEvent(dY);
                } else {//自动加载更多
                    doLoadMoreEvent(dY);
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                // 同ACTION_UP

                if (isPullDownMotion) {
                    //处理下拉结果
                    doPullDownResult();
                } else {
                    //处理滑动加载更多结果
                    doLoadMoreResult();
                }

                break;

            default:
                // throw new IllegalStateException("Unexpected value: " + action);
                break;
        }
        return true;
    }

    /**
     * 处理加载更多
     */
    private void doLoadMoreResult() {
        //手指松开时，如果FooterView,没有完全滑动出来，自动滑动出来
        scrollTo(0, mFooterHeight);
        mScrollY = getScrollY();
        if (!isRefreshing) {
            isRefreshing = true;
            if (listener != null) {
                listener.onLoadMore();
            }
        }

    }

    /**
     * 加载更多完成后调用
     */
    public void completeLoadMore() {
        scrollTo(0, 0);
        mScrollY = 0;
        isRefreshing = false;
        LinearLayoutManager manager = (LinearLayoutManager) mRefreshView.getLayoutManager();
        int count = manager.getItemCount();
        //加载了更多数据
        if (count > lastVisible + 1) {
            mRefreshView.scrollToPosition(lastVisible + 1);
        }
    }

    // 处理加载更多
    private void doLoadMoreEvent(float y) {
        int scrollY = (int) (mScrollY - y);
        if (scrollY < 0) {
            scrollY = 0;
        }


        if (scrollY > mFooterHeight) {
            scrollY = mFooterHeight;
        }
        Log.v("@scrollY", scrollY + "");
        scrollTo(0, scrollY);
    }

    /**
     * 处理释放后的操作
     */
    private void doPullDownResult() {
        //先获取现在滑动到的位置
        mScrollY = getScrollY();
        switch (mCurrentState) {
            case PULL_TO_REFRESH:
                mCurrentState = NORMAL;
                mHeaderView.onNormal();
                smoothScrollTo(0);
                break;
            case RELEASE_TO_REFRESH:
                //松开时，如果是释放刷新，则开始进行刷新动作
                if (!isRefreshing) {
                    //滑动到指定位置
                    smoothScrollTo(-mHeaderHeight);

                    mHeaderView.onRefreshing();
                    isRefreshing = true;
                    if (listener != null) {
                        //执行刷新回调
                        listener.onPullDownRefresh();

                    }
                    //如果当前滑动位置太靠下，则滑动到指定刷新位置
                } else if (mScrollY < -mHeaderHeight) {
                    smoothScrollTo(-mHeaderHeight);
                }
                break;

            default:
                // throw new IllegalStateException("Unexpected value: " + mCurrentState);
                break;
        }
    }

    /**
     * 获取到数据后，调用
     */
    public void completeRefresh() {
        isRefreshing = false;
        mCurrentState = NORMAL;
        smoothScrollTo(0);
    }

    private void doPullDownMoveEvent(float y) {
        int scrollY = (int) (mScrollY - y * RATIO);
        if (scrollY > 0) {
            scrollY = 0;
        }
        if (scrollY < -mMaxScrollHeight) {
            scrollY = -mMaxScrollHeight;
        }
        scrollTo(0, scrollY);
        if (isRefreshing) {
            return;
        }
        //设置相应的状态
        if (scrollY == 0) {
            mCurrentState = NORMAL;
            mHeaderView.onNormal();
        } else if (scrollY <= 0 && scrollY > -mHeaderHeight) {
            mCurrentState = PULL_TO_REFRESH;
            mHeaderView.onPullToRefresh(Math.abs(scrollY));
        } else if (scrollY <= -mHeaderHeight && scrollY >= -mMaxScrollHeight) {
            mCurrentState = RELEASE_TO_REFRESH;
            mHeaderView.onReleaseToRefresh(Math.abs(scrollY));
        }
    }

    /**
     * 从当前位置滑动到指定位置
     *
     * @param y 滑动到的位置
     */
    private void smoothScrollTo(int y) {
        int dY = y - mScrollY;
        mScroller.startScroll(0, mScrollY, 0, dY, 500);
        invalidate();

    }

    private RecyclerView getRefreshView() {
        mRefreshView = new RecyclerView(mContext);
        mRefreshView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mRefreshView.addItemDecoration(new CustomItemDecoration(mContext, CustomItemDecoration.VERTICAL_LIST));
        return mRefreshView;
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        mRefreshView.setAdapter(adapter);
    }

    /**
     * 判断列表是否在最顶端
     *
     * @return
     */
    private boolean isEnablePullDown() {
        LinearLayoutManager manager = (LinearLayoutManager) mRefreshView.getLayoutManager();
        int firstVisible = manager.findFirstVisibleItemPosition();
        //当前还没有数据，可以进行下拉
        if (manager.getItemCount() == 0) {
            return true;
        }
        return firstVisible == 0 && manager.getChildAt(0).getTop() == 0;
    }

    /**
     * 判断列表是否滑动到了最底部
     *
     * @return
     */
    private boolean isEnableLoadMore() {
        LinearLayoutManager manager = (LinearLayoutManager) mRefreshView.getLayoutManager();
        lastVisible = manager.findLastVisibleItemPosition();
        int totalCount = manager.getItemCount();
        //如果没有数据，只能下拉刷新
        if (totalCount == 0) {
            return false;
        }
        int bottom = manager.findViewByPosition(lastVisible).getBottom();
        int decorHeight = manager.getBottomDecorationHeight(mRefreshView.getChildAt(0));
        //最后一个child的底部位置在当前视图的上面
        return totalCount == lastVisible + 1 && bottom + decorHeight <= getMeasuredHeight();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY());
            mScrollY = mScroller.getCurrY();
            invalidate();
        }
    }

    public static final int TYPE_HIDE = 1;
    public static final int TYPE_HAS_MORE_DATA = 2;
    public static final int TYPE_NO_MORE_DATA = 3;

    /**
     * 设置Footer的内容
     */
    public void setFooterViewState(int viewType) {
        switch (viewType) {
            case TYPE_HAS_MORE_DATA:
                mFooterView.setVisibility(View.VISIBLE);
                mFooterView.onRefreshing();
                break;
            case TYPE_NO_MORE_DATA:
                mFooterView.setVisibility(View.VISIBLE);
                mFooterView.onNoData();
                break;
            default:
                mFooterView.setVisibility(View.GONE);
                break;
        }
    }

    public interface OnRefreshListener {
        void onPullDownRefresh();

        void onLoadMore();
    }

    public void setOnRefreshListener(OnRefreshListener listener) {

        this.listener = listener;
    }
}
