package com.android.notreddit.service

import com.android.notreddit.model.Comment
import com.android.notreddit.model.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("posts")
    fun getAllPosts() : Call<List<Post>>

    @GET("comments")
    fun getComments(@Query("postId") postId: Int) : Call<List<Comment>>
}