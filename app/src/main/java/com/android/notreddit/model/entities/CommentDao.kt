package com.android.notreddit.model.entities

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
abstract class CommentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertComments(comments: List<CommentEntity>)

    @Query("SELECT * FROM Comments WHERE postId = :postId")
    abstract fun findCommentsForPost(postId: Int) : LiveData<List<CommentEntity>>
}