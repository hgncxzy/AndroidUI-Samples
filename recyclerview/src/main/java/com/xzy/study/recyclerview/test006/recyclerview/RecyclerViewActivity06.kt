package com.xzy.study.recyclerview.test006.recyclerview

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.xzy.study.recyclerview.R
import com.xzy.study.recyclerview.test006.recyclerview.adapter.DeliveredAdapter
import com.xzy.study.recyclerview.test006.recyclerview.data.RvData.initData
import com.xzy.study.recyclerview.test006.recyclerview.data.RvData.loadMoreData
import com.xzy.study.recyclerview.test006.recyclerview.data.RvData.refreshData
import com.xzy.study.recyclerview.test006.recyclerview.item.anim.ExpandableViewHoldersUtil
import com.xzy.study.recyclerview.test006.recyclerview.layoutmanager.RecyclerViewNoBugLinearLayoutManager
import kotlinx.android.synthetic.main.activity_main06.*

/**
 *
 *https://blog.csdn.net/android_freshman/article/details/94354088
 * 或者参考我的博客导航目录 recyclerview 系列
 * @author xzy
 */
class RecyclerViewActivity06 : AppCompatActivity() {

    var isOk = true
    private val pageTag = "DeliveredListFragment"
    private var myAdapter: DeliveredAdapter = DeliveredAdapter(this)
    private var currPage = 1
    private val totalPage = 3

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main06)

        // 设置可折叠
        ExpandableViewHoldersUtil.instance.init().setNeedExplanedOnlyOne(true)
        ExpandableViewHoldersUtil.instance.resetExpanedList()

        val linearLayoutManager = RecyclerViewNoBugLinearLayoutManager(this)
        recyclerview.layoutManager = linearLayoutManager
        recyclerview.adapter = myAdapter
        myAdapter.notifySetListDataChanged(initData())

        // 按钮点击事件
        myAdapter.setOnItemClickListener(object :
            DeliveredAdapter.OnItemClickListener {
            override fun onButtonClicked(view: View?, position: Int, value: String?) {
                Log.d("xzy", "$position--$value")

            }
        })

        // 下拉刷新
        refreshLayout.setOnRefreshListener {
            Handler(Looper.getMainLooper()).postDelayed({
                refreshLayout.isRefreshing = false
                currPage = 1
                myAdapter.hasMore(true)
                ExpandableViewHoldersUtil.instance.resetExpanedList()
                myAdapter.notifySetListDataChanged(refreshData())
            }, 2000)
        }

        // 上拉加载更多
        recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val last = linearLayoutManager.findLastVisibleItemPosition()
                val sum = myAdapter.itemCount
                if (newState == RecyclerView.SCROLL_STATE_IDLE &&
                    last + 1 == sum &&
                    !refreshLayout.isRefreshing
                ) {
                    if (isOk) {
                        isOk = false
                        myAdapter.hasMore(true)
                        currPage++
                        Thread {
                            Thread.sleep(1000)
                            if (currPage <= 3) {
                                runOnUiThread {
                                    myAdapter.hasMore(true)
                                    myAdapter.notifySetListDataChanged(loadMoreData())
                                    isOk = true
                                }
                            } else {
                                runOnUiThread {
                                    myAdapter.hasMore(false)
                                    myAdapter.notifyDataSetChanged()
                                    isOk = true
                                }
                            }
                        }.start()
                    }
                }
            }
        })
    }
}