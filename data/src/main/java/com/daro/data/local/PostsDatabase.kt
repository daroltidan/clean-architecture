package com.daro.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.daro.data.local.dao.PostsDao
import com.daro.data.local.entities.PostEntity

private const val DATABASE_NAME = "posts_database"

@Database(
    //always add these in alphabetical order!
    entities = [
        PostEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class PostsDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: PostsDatabase? = null

        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, PostsDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }

    //add in alphabetical order
    abstract fun postsDao(): PostsDao

}

