package com.android.notreddit.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.notreddit.model.ApiError
import com.android.notreddit.model.BaseRepository
import com.android.notreddit.model.Comment
import com.android.notreddit.model.entities.CommentEntity
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentRepository(application: Application, postId: Int? = null) : BaseRepository(application) {

    private val commentDao = database.commentDao()
    private var postId: Int = postId ?: -1
    var mComments: LiveData<List<CommentEntity>> = commentDao.findCommentsForPost(this.postId)
    var mCommentsSaved: MutableLiveData<Boolean> = MutableLiveData()

    fun getCommentsForPostApi(postId: Int) {
        this.postId = postId
        service.getComments(postId)
            .enqueue(object : Callback<List<Comment>> {
                override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                    errorResponse.postValue(
                        ApiError(
                            t.message
                        )
                    )
                }

                override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                    saveCommentsToDbFromResponse(response.body())
                }
            })
    }

    private fun saveCommentsToDbFromResponse(comments: List<Comment>?) {
        if (comments != null) {
            val commentsArr = ArrayList<CommentEntity>()
            comments.forEach { commentsArr.add(CommentEntity(it)) }
            scope.launch {
                launch { commentDao.insertComments(commentsArr) }.join()
                mCommentsSaved.postValue(true)
            }
        }
    }
}