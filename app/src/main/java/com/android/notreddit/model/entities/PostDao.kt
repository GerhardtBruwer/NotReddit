package com.android.notreddit.model.entities

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
abstract class PostDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertAllPosts(posts: List<PostEntity>)

    @Query("SELECT * FROM posts")
    abstract fun findAllPosts() : LiveData<List<PostEntity>>
}