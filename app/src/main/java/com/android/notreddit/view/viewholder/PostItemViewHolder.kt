package com.android.notreddit.view.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.android.notreddit.R
import com.android.notreddit.common.recycler.BaseViewHolder
import com.android.notreddit.common.recycler.BaseViewHolderFactory
import com.android.notreddit.common.recycler.OnListItemClickListener
import com.android.notreddit.model.entities.PostEntity
import kotlinx.android.synthetic.main.post_item.view.*

class PostItemViewHolder(itemView: View, private val listener: OnListItemClickListener) : BaseViewHolder<PostEntity>(itemView) {

    override fun bindViewHolder(data: PostEntity) {
        super.bindViewHolder(data)
        itemView.tvTitle.text = data.title
        itemView.tvBody.text = data.body
        itemView.setOnClickListener { if (data.id != null) listener.onItemClick(data.id!!) }
    }
}

class PostItemViewHolderFactory(private val listener: OnListItemClickListener) : BaseViewHolderFactory {

    override fun getViewType(item: Any?): Int {
        return -1
    }

    override fun build(view: View, type: Int?): RecyclerView.ViewHolder {
        return PostItemViewHolder(view, listener)
    }

    override fun getLayout(layout: Int): Int {
        return R.layout.post_item
    }
}