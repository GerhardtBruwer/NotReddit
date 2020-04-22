package com.android.notreddit.common.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private var mLisItems: List<T>

    constructor() {mLisItems = emptyList()}

    constructor(listItems: List<T>) {
        this.mLisItems = listItems
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return createViewHolder(
            LayoutInflater.from(parent.context).inflate(
                getLayout(viewType),
                parent,
                false
            ),
            viewType
        )
    }

    fun setItems(listItems: List<T>) {
        this.mLisItems = listItems
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mLisItems.size
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BaseViewHolder<T>).bindViewHolder(mLisItems[position])
    }

    abstract fun createViewHolder(view: View, viewType: Int) : RecyclerView.ViewHolder

    protected abstract fun getLayout(viewType: Int) : Int
}