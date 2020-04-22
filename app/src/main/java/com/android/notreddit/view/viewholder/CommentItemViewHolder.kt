package com.android.notreddit.view.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.android.notreddit.R
import com.android.notreddit.common.recycler.BaseViewHolder
import com.android.notreddit.common.recycler.BaseViewHolderFactory
import com.android.notreddit.model.entities.CommentEntity
import kotlinx.android.synthetic.main.comment_item.view.*

class CommentItemViewHolder(itemView: View) : BaseViewHolder<CommentEntity>(itemView) {

    override fun bindViewHolder(data: CommentEntity) {
        super.bindViewHolder(data)
        itemView.tvName.text = data.name
        itemView.tvBody.text = data.body
        itemView.tvEmail.text = data.email
    }
}

class CommentItemViewHolderFactory : BaseViewHolderFactory {

    override fun getViewType(item: Any?): Int {
        return -1
    }

    override fun build(view: View, type: Int?): RecyclerView.ViewHolder {
        return  CommentItemViewHolder(view)
    }

    override fun getLayout(layout: Int): Int {
        return R.layout.comment_item
    }
}