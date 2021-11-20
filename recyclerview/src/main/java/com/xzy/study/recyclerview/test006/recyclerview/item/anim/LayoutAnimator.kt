package com.xzy.study.recyclerview.test006.recyclerview.item.anim

import android.animation.*
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.view.View

object LayoutAnimator {
    // 使用ValueAnimator.ofInt生成一系列高度值，然后添加上面的监听
    fun ofHeight(view: View, start: Int, end: Int): Animator {
        val animator = ValueAnimator.ofInt(start, end)
        animator.duration = ExpandableViewHoldersUtil.animalDuration
        animator.addUpdateListener(LayoutHeightUpdateListener(view))
        return animator
    }

    // 监听动画的变化，不断设定view的高度值
    class LayoutHeightUpdateListener(private val mView: View) : AnimatorUpdateListener {
        override fun onAnimationUpdate(animation: ValueAnimator) {
            val lp = mView.layoutParams
            lp.height = animation.animatedValue as Int
            mView.layoutParams = lp
        }
    }
}
