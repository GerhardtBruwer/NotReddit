package com.android.notreddit.model.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.notreddit.model.Comment

@Entity(tableName = "Comments")
class CommentEntity(

    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int?,

    @NonNull
    @ColumnInfo(name = "postId")
    var postId: Int?,

    @ColumnInfo(name = "name")
    var name: String? = "",

    @ColumnInfo(name = "email")
    var email: String? = "",

    @ColumnInfo(name = "body")
    var body: String? = ""
) {
    constructor(comment: Comment) : this (
        comment.id,
        comment.postId,
        comment.name,
        comment.email,
        comment.body
    )
}