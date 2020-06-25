package com.daro.data.local.dao

import androidx.room.*
import com.daro.data.local.entities.PostEntity


@Dao
interface PostsDao {
    @Query("SELECT * FROM posts")
    suspend fun getAll(): List<PostEntity>

    @Query("SELECT * FROM posts WHERE id  = :id")
    suspend fun getPost(id: Int): List<PostEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(list: List<PostEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(postEntity: PostEntity)

    @Delete
    suspend fun delete(postEntity: PostEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(postEntity: PostEntity)
}