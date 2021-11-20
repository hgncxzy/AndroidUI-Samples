package com.xzy.study.recyclerview.test006.recyclerview.item.anim

import android.animation.*
import android.view.ViewGroup
import android.view.View
import androidx.recyclerview.widget.RecyclerView

object ViewHolderAnimator {
    // OpenHolder中动画的具体操作方法
    fun ofItemViewHeight(holder: RecyclerView.ViewHolder): Animator {
        val parent = holder.itemView.parent as View

        // 测量扩展动画的起始高度和结束高度
        val start = holder.itemView.measuredHeight
        holder.itemView.measure(
            View.MeasureSpec.makeMeasureSpec(
                parent.measuredWidth,
                View.MeasureSpec.AT_MOST
            ),
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        )
        val end = holder.itemView.measuredHeight
        val animator = LayoutAnimator.ofHeight(holder.itemView, start, end) // 具体的展开动画

        // 设定该Item在动画开始结束和取消时能否被recycle
        animator.addListener(ViewHolderAnimatorListener(holder))

        // 设定结束时这个Item的宽高
        animator.addListener(
            LayoutParamsAnimatorListener(
                holder.itemView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        )
        return animator
    }

    // 设定在动画开始结束和取消状态下是否可以被回收
    class ViewHolderAnimatorListener(private val mHolder: RecyclerView.ViewHolder) :
        AnimatorListenerAdapter() {
        override fun onAnimationStart(animation: Animator) { // 开始时
            mHolder.setIsRecyclable(false)
        }

        override fun onAnimationEnd(animation: Animator) { // 结束时
            mHolder.setIsRecyclable(true)
        }

        override fun onAnimationCancel(animation: Animator) { // 取消时
            mHolder.setIsRecyclable(true)
        }
    }

    // 设定在动画结束后View的宽度和高度分别为 match_parent, warp_content
    class LayoutParamsAnimatorListener(
        private val mView: View,
        private val mParamsWidth: Int,
        private val mParamsHeight: Int
    ) : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
            val params = mView.layoutParams
            params.width = mParamsWidth
            params.height = mParamsHeight
            mView.layoutParams = params
        }
    }
}
