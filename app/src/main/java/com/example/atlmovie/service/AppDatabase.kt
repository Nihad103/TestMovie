package com.example.atlmovie.service

import com.example.atlmovie.dao.MyListDao
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.atlmovie.dao.CardDao
import com.example.atlmovie.dao.DownloadDao
import com.example.atlmovie.model.CardModel
import com.example.atlmovie.model.download.DownloadEntity
import com.example.atlmovie.model.mylist.MyListEntity

@Database(
    entities = [MyListEntity::class, DownloadEntity::class, CardModel::class],
    version = 5,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun myListDao(): MyListDao
    abstract fun downloadDao(): DownloadDao
    abstract fun cardDao(): CardDao
}