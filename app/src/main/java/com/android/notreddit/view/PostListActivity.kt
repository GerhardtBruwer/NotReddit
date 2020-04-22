package com.android.notreddit.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.notreddit.R
import com.android.notreddit.common.recycler.OnListItemClickListener
import com.android.notreddit.common.util.displayErrorMessage
import com.android.notreddit.model.entities.PostEntity
import com.android.notreddit.view.viewholder.PostItemViewHolderFactory
import com.android.notreddit.viewmodel.AppViewModel
import kotlinx.android.synthetic.main.activity_post.*
import kotlinx.android.synthetic.main.item_list.*

class PostListActivity : AppCompatActivity(), OnListItemClickListener {

    private var posts: List<PostEntity> = emptyList()

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        setSupportActionBar(toolbar)
        toolbar.title = title

        if (item_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }
        // Just to get rid of the "No adapter attached; skipping layout" in the logs
        item_list.create(PostItemViewHolderFactory(this), null)
        val mViewModel = ViewModelProvider(this).get(AppViewModel::class.java)
        mViewModel.posts.observe(this, Observer { posts ->
            this.posts = posts
            item_list.create(PostItemViewHolderFactory(this), this.posts)
        })

        mViewModel.errCommentRepo.observe(this, Observer { error -> displayErrorMessage(error) })
    }


    override fun onItemClick(position: Int) {
        val postId = posts[position].id
        if (twoPane) {
            val fragment = CommentDetailFragment()
                .apply {
                    arguments = Bundle().apply {
                        if (postId != null) putInt(CommentDetailFragment.ARG_POST_ID, postId)
                    }
                }
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.item_detail_container, fragment)
                .commit()
        } else {
            val intent = Intent(this, CommentDetailActivity::class.java).apply {
                putExtra(CommentDetailFragment.ARG_POST_ID, postId)
            }
            startActivity(intent)
        }
    }
}
