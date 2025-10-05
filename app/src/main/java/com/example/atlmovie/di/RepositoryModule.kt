package com.example.atlmovie.di

import com.example.atlmovie.service.MovieRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { MovieRepository(get(), get(), get(), get()) }
}