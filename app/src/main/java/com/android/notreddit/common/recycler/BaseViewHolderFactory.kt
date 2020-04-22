package com.android.notreddit.common.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView

interface BaseViewHolderFactory {

    fun getViewType(item: Any?) : Int

    fun build(view: View, type: Int? = null) : RecyclerView.ViewHolder

    fun getLayout(layout: Int) : Int
}