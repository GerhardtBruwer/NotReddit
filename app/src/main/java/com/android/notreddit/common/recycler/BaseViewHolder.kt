package com.android.notreddit.common.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    open var data: T? = null

    open fun bindViewHolder(data: T) {
        this.data = data
    }
}