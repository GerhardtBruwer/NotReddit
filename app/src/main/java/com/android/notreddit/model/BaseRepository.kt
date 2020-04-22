package com.android.notreddit.model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.android.notreddit.database.NotRedditDatabase
import com.android.notreddit.service.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class BaseRepository(mApplication: Application) {

    val errorResponse = MutableLiveData<ApiError>()
    protected val service = getApiService()
    protected val database = NotRedditDatabase.getDatabase(mApplication.applicationContext)

    private val job = Job()
    protected val scope = CoroutineScope(Dispatchers.IO + job)

    private fun getApiService(): ApiService {
        val client = OkHttpClient.Builder().build()
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build().create(ApiService::class.java)
    }
}