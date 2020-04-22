package com.android.notreddit.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.notreddit.R
import com.android.notreddit.common.util.displayErrorMessage
import com.android.notreddit.view.viewholder.CommentItemViewHolderFactory
import com.android.notreddit.viewmodel.AppViewModel
import kotlinx.android.synthetic.main.fragment_comment.view.*
import kotlinx.android.synthetic.main.item_list.view.*

class CommentDetailFragment : Fragment() {

    private var postId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_POST_ID)) {
                postId = it.getInt(ARG_POST_ID)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val mView = inflater.inflate(R.layout.fragment_comment, container, false)
        val mViewModel = ViewModelProvider(this).get(AppViewModel::class.java)
        mView.item_list.create(CommentItemViewHolderFactory(), null)
        mView.vProgress.show()
        mViewModel.getComments(postId)
        mViewModel.updateUI.observe(viewLifecycleOwner, Observer { if (it) {
            if (mViewModel.setPostId(postId)) {
                mViewModel.comments.observe(viewLifecycleOwner, Observer { comments ->
                    mView.item_list.create(CommentItemViewHolderFactory(), comments)
                    mView.vProgress.hide()
                })
            }
        }})
        mViewModel.errCommentRepo.observe(viewLifecycleOwner, Observer { error -> mView.context.displayErrorMessage(error) })

        return mView
    }

    companion object {

        const val ARG_POST_ID = "post_id"
    }
}
