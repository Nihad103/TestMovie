package com.example.atlmovie.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.atlmovie.model.download.DownloadEntity
import com.example.atlmovie.model.mylist.MyListEntity

@Dao
interface DownloadDao {
    @Query("SELECT * FROM download")
    suspend fun getAllDownloads(): List<DownloadEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDownload(movie: DownloadEntity)

    @Delete
    suspend fun deleteDownload(movie: DownloadEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM download WHERE id = :movieId)")
    suspend fun isDownloadExist(movieId: Int): Boolean
}