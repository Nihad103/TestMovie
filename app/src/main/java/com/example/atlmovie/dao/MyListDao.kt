package com.example.atlmovie.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.atlmovie.model.mylist.MyListEntity

@Dao
interface MyListDao {
    @Query("SELECT * FROM mylist")
    suspend fun getAllMovies(): List<MyListEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MyListEntity)

    @Delete
    suspend fun deleteMovie(movie: MyListEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM mylist WHERE id = :movieId)")
    suspend fun isMovieExist(movieId: Int): Boolean
}