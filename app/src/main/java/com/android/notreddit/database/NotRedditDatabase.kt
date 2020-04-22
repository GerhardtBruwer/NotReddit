package com.android.notreddit.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.notreddit.model.entities.CommentDao
import com.android.notreddit.model.entities.CommentEntity
import com.android.notreddit.model.entities.PostDao
import com.android.notreddit.model.entities.PostEntity

@Database (
    entities = [
        PostEntity::class,
        CommentEntity::class
    ],
    version = 1,
    // This would be set to true for migration testing - for security reasons its currently set to false
    exportSchema = false
)
abstract class NotRedditDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao
    abstract fun commentDao(): CommentDao

    companion object {

        @Volatile
        private var INSTANCE: NotRedditDatabase? = null

        fun getDatabase(context: Context) : NotRedditDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance

            val dbName = "NotRedditDatabase"

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotRedditDatabase::class.java,
                    dbName
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}