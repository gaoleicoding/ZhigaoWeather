package com.weather.zhigao.adapter.divider

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

import com.weather.zhigao.utils.ScreenUtils


class SpacesItemDecoration(context: Context, space: Int) : RecyclerView.ItemDecoration() {

    private val space: Int

    init {
        this.space = ScreenUtils.dp2px(context, space.toFloat())
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.left = space
        outRect.right = space
        outRect.bottom = space

        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.top = space
        } else {
            outRect.top = 0
        }
    }
}