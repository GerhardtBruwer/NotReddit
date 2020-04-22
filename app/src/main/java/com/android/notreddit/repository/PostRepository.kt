package com.android.notreddit.repository

import android.app.Application
import com.android.notreddit.model.ApiError
import com.android.notreddit.model.BaseRepository
import com.android.notreddit.model.Post
import com.android.notreddit.model.entities.PostEntity
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostRepository(application: Application) : BaseRepository(application) {

    private val postDao = database.postDao()
    val mPosts = postDao.findAllPosts()

    fun getAllPostsApi() {
        service.getAllPosts()
            .enqueue(object : Callback<List<Post>> {
                override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                    errorResponse.postValue(
                        ApiError(
                            t.message
                        )
                    )
                }

                override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                    savePostsToDbFromResponse(response.body())
                }
            })
    }

    private fun savePostsToDbFromResponse(posts: List<Post>?) {
        if (posts != null) {
            val postArr = ArrayList<PostEntity>()
            posts.forEach { postArr.add(PostEntity(it)) }
            scope.launch { postDao.insertAllPosts(postArr) }
        }
    }
}