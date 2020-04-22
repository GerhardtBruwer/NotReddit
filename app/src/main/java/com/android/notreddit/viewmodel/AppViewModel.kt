package com.android.notreddit.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.android.notreddit.repository.CommentRepository
import com.android.notreddit.repository.PostRepository
import com.android.notreddit.model.entities.CommentEntity


class AppViewModel(application: Application) : AndroidViewModel(application) {

    private var commentRepo =
        CommentRepository(application)
    private val postRepo =
        PostRepository(application)
    val posts = postRepo.mPosts
    val updateUI = commentRepo.mCommentsSaved
    val errPostRepo = postRepo.errorResponse
    val errCommentRepo = commentRepo.errorResponse

    lateinit var comments: LiveData<List<CommentEntity>>

    var postId: Int = -1

    init {
        postRepo.getAllPostsApi()
    }

    fun setPostId(id: Int) : Boolean {
        if (id != -1) {
            postId = id
            commentRepo = CommentRepository(
                getApplication(),
                postId
            )
            comments = commentRepo.mComments
            return true
        }
        return false
    }

    fun getComments(postId: Int) {
        commentRepo.getCommentsForPostApi(postId)
    }
}