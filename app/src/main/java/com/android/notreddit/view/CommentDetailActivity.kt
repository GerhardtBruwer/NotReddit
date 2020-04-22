package com.android.notreddit.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import com.android.notreddit.R
import kotlinx.android.synthetic.main.activity_comment.*

class CommentDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)

        setSupportActionBar(toolbar)
        toolbar.title = title
        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            val fragment = CommentDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(
                        CommentDetailFragment.ARG_POST_ID,
                            intent.getIntExtra(CommentDetailFragment.ARG_POST_ID, 0))
                }
            }

            supportFragmentManager.beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                android.R.id.home -> {
                    navigateUpTo(Intent(this, PostListActivity::class.java))
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
}
