package com.android.notreddit.model.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.notreddit.model.Post

@Entity(tableName = "Posts")
class PostEntity(
    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int?,

    @NonNull
    @ColumnInfo(name = "userId")
    var userId: Int?,

    @ColumnInfo(name = "title")
    var title: String? = "",

    @ColumnInfo(name = "body")
    var body: String? = ""
) {
    constructor(post: Post): this (
        post.id,
        post.userId,
        post.title,
        post.body
    )
}