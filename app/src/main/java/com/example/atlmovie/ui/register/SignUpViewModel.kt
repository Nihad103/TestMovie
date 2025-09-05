package com.example.atlmovie.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class SignUpViewModel(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private var _isSuccess = MutableLiveData<Boolean>()
    val isSuccess : LiveData<Boolean> = _isSuccess

    private var _isError = MutableLiveData<String>()
    val isError : LiveData<String> = _isError

    fun register(email:String, password:String) {
        viewModelScope.launch {
            try {
                val response = firebaseAuth.createUserWithEmailAndPassword(email, password).await()

                if (response.user?.uid != null) {
                    _isSuccess.value = true
                } else {
                    _isSuccess.value = false
                    _isError.postValue("Exception")
                }

            } catch (e:Exception) {
                _isError.postValue(e.localizedMessage?.toString() ?: "Exception!")
            }
        }
    }
}