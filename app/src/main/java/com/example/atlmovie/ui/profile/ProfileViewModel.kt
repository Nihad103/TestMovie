package com.example.atlmovie.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class ProfileViewModel(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _logoutEvent = MutableLiveData<Boolean>()
    val logoutEvent: LiveData<Boolean> = _logoutEvent

    fun logout() {
        firebaseAuth.signOut()
        _logoutEvent.value = true
    }
}