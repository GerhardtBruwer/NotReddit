package com.android.notreddit.common.recycler

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BaseRecycler @JvmOverloads constructor(ctx: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0)
    : RecyclerView(ctx, attributeSet, defStyleAttr)  {

    lateinit var mViewHolderFactory: BaseViewHolderFactory
    private var mListItems: List<Any> = emptyList()

    fun create(viewHolderFactory: BaseViewHolderFactory, initialData: List<Any>?) {
        this.mListItems = initialData ?: emptyList()
        this.mViewHolderFactory = viewHolderFactory
        this.setAdapter(context, this.mListItems)
    }

    private fun <T>setAdapter(context: Context, data: List<T>) {

        this.layoutManager = LinearLayoutManager(context)
        this.adapter = object : BaseRecyclerAdapter<T>(data) {

            override fun createViewHolder(view: View, viewType: Int): ViewHolder {
                return mViewHolderFactory.build(view, viewType)
            }

            override fun getLayout(viewType: Int): Int {
                return mViewHolderFactory.getLayout(viewType)
            }

            override fun getItemId(position: Int): Long {
                return position.toLong()
            }
        }
    }
}