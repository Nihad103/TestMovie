package com.example.atlmovie.di

import androidx.lifecycle.SavedStateHandle
import com.example.atlmovie.ui.detail.DetailViewModel
import com.example.atlmovie.ui.detail.morelikethis.MoreLikeThisViewModel
import com.example.atlmovie.ui.detail.trailers.TrailersViewModel
import com.example.atlmovie.ui.explore.ExploreViewModel
import com.example.atlmovie.ui.home.HomeViewModel
import com.example.atlmovie.ui.login.LoginViewModel
import com.example.atlmovie.ui.notifications.NotificationsViewModel
import com.example.atlmovie.ui.register.SignUpViewModel
import com.example.atlmovie.ui.seeall.SeeAllViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SeeAllViewModel(get(), SavedStateHandle()) }
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { MoreLikeThisViewModel(get()) }
    viewModel { TrailersViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { SignUpViewModel(get()) }
    viewModel { NotificationsViewModel(get()) }
    viewModel { ExploreViewModel(get()) }
}
