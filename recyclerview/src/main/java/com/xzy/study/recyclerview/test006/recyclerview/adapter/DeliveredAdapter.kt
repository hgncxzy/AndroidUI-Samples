

package com.xzy.study.recyclerview.test006.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xzy.study.recyclerview.test006.recyclerview.data.RvData.testData
import com.xzy.study.recyclerview.test006.recyclerview.item.anim.ExpandableViewHoldersUtil
import com.xzy.study.recyclerview.R

/**
 *
 * @author ：created by xzy.
 * @date ：2021/11/9
 */
class DeliveredAdapter(var context:Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var stringArrayList = ArrayList<String>()
    lateinit var keepOne: ExpandableViewHoldersUtil.KeepOneHolder<ItemViewHolder>
    private val typeItem = 0
    private val typeFoot = 1
    private var hasMore = true
    private var mOnItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onButtonClicked(view: View?, position: Int, value: String?)
    }

    fun setOnItemClickListener(clickListener: OnItemClickListener?) {
        mOnItemClickListener = clickListener
    }

    fun notifySetListDataChanged(stringArrayList: ArrayList<String>) {
        this.stringArrayList = stringArrayList
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount - 1) {
            typeFoot
        } else typeItem
    }

    override fun getItemCount(): Int {
        return stringArrayList.size + 1
    }

    fun hasMore(hasMoreData: Boolean) {
        hasMore = hasMoreData
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == typeItem) {
            val view = LayoutInflater.from(context)
                .inflate(
                    R.layout.item_order_delivered_list,
                    viewGroup,
                    false
                )
            return ItemViewHolder(view)
        } else {
            val view = LayoutInflater.from(context)
                .inflate(
                    R.layout.item_rv_foot,
                    viewGroup,
                    false
                )
            return FootViewHolder(view)
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder is ItemViewHolder) {
            viewHolder.tvTitle.text = stringArrayList[position] + " po=" + position

            val itemAdapter = ItemAdapter(context)
            viewHolder.itemRecyclerView.layoutManager = LinearLayoutManager(context)
            viewHolder.itemRecyclerView.adapter = itemAdapter
            itemAdapter.setStringArrayList(testData())
            itemAdapter.notifyDataSetChanged()

            itemAdapter.setOnItemClickListener(object : ItemAdapter.OnItemClickListener {
                override fun onButtonClicked(view: View?, position: Int, value: String?) {
                    mOnItemClickListener?.onButtonClicked(view, position, value)
                }
            })

            keepOne.bind(viewHolder, position)
            viewHolder.tvTitle.setOnClickListener {
                keepOne.toggle(viewHolder)
            }
            viewHolder.lvArrowBtn.setOnClickListener { keepOne.toggle(viewHolder) }
        } else {
            if (hasMore) {
                (viewHolder as FootViewHolder).tvFoot.text = "Loading..."
            } else {
                (viewHolder as FootViewHolder).tvFoot.text = "～No more data～"
            }
        }
    }

    // 普通 item viewHolder
    inner class ItemViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView),
        ExpandableViewHoldersUtil.Expandable {
        var tvTitle: TextView =
            itemView.findViewById(R.id.item_user_concern_title)
        private var arrowImage: ImageView =
            itemView.findViewById(R.id.item_user_concern_arrow_image)
        var lvArrowBtn: LinearLayout =
            itemView.findViewById(R.id.item_user_concern_arrow)
        private var lvLinearlayout: LinearLayout =
            itemView.findViewById(R.id.item_user_concern_link_layout)
        var itemRecyclerView: RecyclerView =
            itemView.findViewById(R.id.item_recyclerview)
        override val expandView: View
            get() = lvLinearlayout

        override fun doCustomAnim(isOpen: Boolean) {
            if (isOpen) {
                ExpandableViewHoldersUtil.instance.rotateExpandIcon(arrowImage, 180f, 0f)
            } else {
                ExpandableViewHoldersUtil.instance.rotateExpandIcon(arrowImage, 0f, 180f)
            }
        }

        init {
            keepOne = ExpandableViewHoldersUtil.instance.getKeepOneHolder()
            lvLinearlayout.visibility = View.GONE
            lvLinearlayout.alpha = 0f
        }
    }

    // 脚的 viewHolder
    internal class FootViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvFoot: TextView =
            itemView.findViewById(R.id.tv_foot)
    }

    // 这个是折叠展开后的数据列表适配器
    class ItemAdapter(var context:Context) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
        private var stringArrayList = ArrayList<String>()
        private var mOnItemClickListener: OnItemClickListener? = null

        interface OnItemClickListener {
            fun onButtonClicked(view: View?, position: Int, value: String?)
        }

        fun setOnItemClickListener(clickListener: OnItemClickListener) {
            mOnItemClickListener = clickListener
        }

        fun setStringArrayList(stringArrayList: ArrayList<String>) {
            this.stringArrayList = stringArrayList
        }

        override fun getItemCount(): Int {
            return stringArrayList.size
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(context)
                .inflate(R.layout.item_order_delivered_list_content, viewGroup, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            viewHolder.tvAwb.text = "YT82719205718275"
            viewHolder.tvRecipient.text = "135 0284 5175"
            viewHolder.tvIntoTime.text = "2021.10.12 12:26"
            if (position == stringArrayList.size - 1) {
                viewHolder.tvSolidLine.visibility = View.GONE
            } else {
                viewHolder.tvSolidLine.visibility = View.VISIBLE
            }
            viewHolder.btnIntoRecord.setOnClickListener {
                mOnItemClickListener?.onButtonClicked(it, position, "btnIntoRecord")
            }
            viewHolder.btnTakeCode.setOnClickListener {
                mOnItemClickListener?.onButtonClicked(it, position, "btnTakeCode")
            }
        }

        class ViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {
            var tvAwb: TextView = itemView.findViewById(R.id.tv_awb_value)
            var tvRecipient: TextView = itemView.findViewById(R.id.tv_recipient_value)
            var tvIntoTime: TextView = itemView.findViewById(R.id.tv_into_the_time_value)
            var btnIntoRecord: Button = itemView.findViewById(R.id.btn_into_record)
            var btnTakeCode: Button = itemView.findViewById(R.id.btn_take_a_code)
            var tvSolidLine: TextView = itemView.findViewById(R.id.tv_solid_line)
        }
    }
}
