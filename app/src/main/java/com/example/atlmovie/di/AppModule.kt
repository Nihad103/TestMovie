package com.example.atlmovie.di

import androidx.lifecycle.SavedStateHandle
import com.example.atlmovie.service.MovieRepository
import com.example.atlmovie.ui.seeall.SeeAllViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModules = listOf(networkModule, repositoryModule, viewModelModule, firebaseModule)
