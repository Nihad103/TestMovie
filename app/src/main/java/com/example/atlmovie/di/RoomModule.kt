package com.example.atlmovie.di

import androidx.room.Room
import com.example.atlmovie.service.AppDatabase
import org.koin.dsl.module

val roomModule = module {
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "mylist_db")
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<AppDatabase>().myListDao() }
    single { get<AppDatabase>().downloadDao() }
}
