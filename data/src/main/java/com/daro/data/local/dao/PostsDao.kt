package com.daro.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.daro.data.local.entities.PostEntity

@Dao
interface PostsDao {

    @Query("SELECT * FROM PostEntity")
    suspend fun getAll(): List<PostEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(list: List<PostEntity>)

}