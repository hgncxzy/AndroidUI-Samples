package com.xzy.study.recyclerview.test006.recyclerview.item.anim

import android.animation.*

import android.view.animation.DecelerateInterpolator
import com.xzy.study.recyclerview.test006.recyclerview.item.anim.ViewHolderAnimator.ViewHolderAnimatorListener
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.xzy.study.recyclerview.test006.recyclerview.adapter.DeliveredAdapter
import java.util.ArrayList

/**
 * recyclerView item view 的展开动画
 *
 *
 * 注意：getExpandView 的view 初始透明度为0
 *
 *
 * 例如：UserConcernHolder
 * 1.holder implements ExpandableViewHoldersUtil.Expandable
 * 2.linkRootView.setAlpha(0);
 */
class ExpandableViewHoldersUtil {
    private var needExplanedOnlyOne = false
    fun init(): ExpandableViewHoldersUtil {
        explanedList = ArrayList()
        return this
    }

    /**
     * 点击第二个会收缩前一个 ，remove object
     */
    fun setNeedExplanedOnlyOne(needExplanedOnlyOne: Boolean) {
        this.needExplanedOnlyOne = needExplanedOnlyOne
    }

    private fun addPositionInExpaned(pos: Int) {
        if (!explanedList!!.contains(pos.toString() + "")) {
            explanedList!!.add(pos.toString() + "")
        }
    }

    private fun deletePositionInExpaned(pos: Int) {
        // remove Object 直接写int，会变成index,造成数组越界
        explanedList!!.remove(pos.toString() + "")
    }

    fun resetExpanedList() {
        explanedList!!.clear()
    }

    fun getKeepOneHolder(): KeepOneHolder<DeliveredAdapter.ItemViewHolder> {
        return KeepOneHolder()
    }

    // 自定义列表中icon 的旋转的动画
    fun rotateExpandIcon(imageView: ImageView, from: Float, to: Float) {
        val valueAnimator = ValueAnimator.ofFloat(from, to)
        valueAnimator.interpolator = DecelerateInterpolator()
        valueAnimator.addUpdateListener {
            imageView.rotation = valueAnimator.animatedValue as Float
        }
        valueAnimator.start()
    }

    // 参数介绍：1、holder对象 2、展开部分的View，由holder.getExpandView()方法获取 3、animate参数为true，则有动画效果
    private fun openHolder(holder: RecyclerView.ViewHolder, expandView: View, animate: Boolean) {
        if (animate) {
            expandView.visibility = View.VISIBLE
            // 改变高度的动画
            val animator = ViewHolderAnimator.ofItemViewHeight(holder)

            // 扩展的动画，透明度动画开始
            val alphaAnimator = ObjectAnimator.ofFloat(expandView, View.ALPHA, 1f)
            alphaAnimator.duration = animalDuration + alphaDuration
            alphaAnimator.addListener(ViewHolderAnimatorListener(holder))
            val animatorSet = AnimatorSet()
            animatorSet.playTogether(animator, alphaAnimator)
            animatorSet.start()
        } else { // 为false时直接显示
            expandView.visibility = View.VISIBLE
            expandView.alpha = 1f
        }
    }

    private fun hideExpandView(expandView: View) {
        expandView.visibility = View.GONE
        expandView.alpha = 0f
    }

    // 类似于打开的方法
    private fun closeHolder(holder: RecyclerView.ViewHolder, expandView: View, animate: Boolean) {
        if (animate) {
            expandView.visibility = View.GONE
            val animator = ViewHolderAnimator.ofItemViewHeight(holder)
            expandView.visibility = View.VISIBLE
            animator!!.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    hideExpandView(expandView)
                }

                override fun onAnimationCancel(animation: Animator) {
                    hideExpandView(expandView)
                }
            })
            animator.start()
        } else {
            hideExpandView(expandView)
        }
    }

    // 获取展开部分的View
    interface Expandable {
        val expandView: View
        fun doCustomAnim(isOpen: Boolean)
    }

    // -1表示所有item是关闭状态，opend为pos值的表示pos位置的item为展开的状态
    private var opened = -1

    inner class KeepOneHolder<VH> where VH : RecyclerView.ViewHolder, VH : Expandable? {
        var preOpen = 0

        /**
         * 此方法是在Adapter的onBindViewHolder()方法中调用
         *
         * @param holder holder对象
         * @param pos    下标
         */
        fun bind(holder: VH, pos: Int) {
            if (explanedList!!.contains(pos.toString() + "")) {
                instance.openHolder(holder, holder!!.expandView, false)
            } else {
                instance.closeHolder(holder, holder!!.expandView, false)
            }
        }

        /**
         * 响应ViewHolder的点击事件
         *
         * @param holder holder对象
         */
        fun toggle(holder: VH) {
            val position = holder!!.position
            if (explanedList!!.contains(position.toString() + "")) {
                opened = -1
                deletePositionInExpaned(position)
                holder.doCustomAnim(true)
                instance!!.closeHolder(holder, holder.expandView, true)
            } else {
                preOpen = opened
                opened = position
                addPositionInExpaned(position)
                holder.doCustomAnim(false)
                instance!!.openHolder(holder, holder.expandView, true)

                // 是否要关闭上一个
                if (needExplanedOnlyOne && preOpen != position) {
                    val oldHolder =
                        (holder.itemView.parent as RecyclerView).findViewHolderForPosition(preOpen) as VH?
                    if (oldHolder != null) {
                        Log.e("KeepOneHolder", "oldHolder != null")
                        instance.closeHolder(oldHolder, oldHolder.expandView, true)
                        deletePositionInExpaned(preOpen)
                    }
                }
            }
        }
    }

    companion object {
        var animalDuration: Long = 300
        var alphaDuration: Long = 100
        private var explanedList: ArrayList<String>? = // 缓存的数据
            null
        private var holdersUtil: ExpandableViewHoldersUtil? = null

        /**
         * 获取单例
         */
        val instance: ExpandableViewHoldersUtil
            get() {
                if (holdersUtil == null) {
                    holdersUtil = ExpandableViewHoldersUtil()
                }
                return holdersUtil!!
            }

        fun isExpaned(index: Int): Boolean {
            return explanedList!!.contains(index.toString() + "")
        }
    }
}
