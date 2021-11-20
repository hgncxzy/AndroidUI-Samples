
package com.xzy.study.recyclerview.test006.recyclerview.layoutmanager

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Recycler
import androidx.recyclerview.widget.RecyclerView
import java.lang.IndexOutOfBoundsException

/**
 * @author ：created by xzy.
 * @date ：2021/11/11
 */
class RecyclerViewNoBugLinearLayoutManager : LinearLayoutManager {
    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, orientation: Int, reverseLayout: Boolean) : super(
        context,
        orientation,
        reverseLayout
    ) {
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
    }

    override fun onLayoutChildren(recycler: Recycler, state: RecyclerView.State) {
        try {
            super.onLayoutChildren(recycler, state)
        } catch (e: IndexOutOfBoundsException) {
            e.printStackTrace()
        }
    }
}
