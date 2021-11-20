

package com.xzy.study.recyclerview.test006.recyclerview.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.xzy.study.recyclerview.test006.recyclerview.data.ExpressRoutingItem

import com.xzy.study.recyclerview.R

/**
 *
 * @author ：created by xzy.
 * @date ：2021/11/16
 */
class ExpressRoutingAdapter(var context:Context) :
    RecyclerView.Adapter<ExpressRoutingAdapter.ItemViewHolder>() {
    private var list: ArrayList<ExpressRoutingItem> = ArrayList()
    fun notifySetListDataChanged(data: ArrayList<ExpressRoutingItem>) {
        this.list = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(
                R.layout.item_dialog_express_routing,
                viewGroup,
                false
            )
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ItemViewHolder, position: Int) {
        viewHolder.tvDescribe.text = list[position].describe
        viewHolder.tvTime.text = list[position].time
        if (position == 0) {
            viewHolder.tvDescribe.setTextColor(Color.parseColor("#85CF26"))
            viewHolder.tvTime.setTextColor(Color.parseColor("#85CF26"))
        } else {
            viewHolder.tvDescribe.setTextColor(Color.parseColor("#333333"))
            viewHolder.tvTime.setTextColor(Color.parseColor("#999999"))
        }
    }

    // 普通 item viewHolder
    inner class ItemViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val tvDescribe: TextView = itemView.findViewById(R.id.tv_describe)
        val tvTime: TextView = itemView.findViewById(R.id.tv_time)
    }
}
