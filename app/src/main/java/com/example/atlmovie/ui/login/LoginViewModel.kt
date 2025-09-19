package com.example.atlmovie.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LoginViewModel(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private var _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = _isSuccess

    private var _isError = MutableLiveData<String>()
    val isError: LiveData<String> = _isError

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = firebaseAuth.signInWithEmailAndPassword(email, password).await()

                if (response.user?.uid != null) {
                    _isSuccess.value = true
                } else {
                    _isSuccess.value = false
                    _isError.postValue("Exception")
                }

            } catch (e: FirebaseAuthInvalidUserException) {
                _isError.value = "email"

            } catch (e: FirebaseAuthInvalidCredentialsException) {
                _isError.value = "password"

            } catch (e: Exception) {
                _isError.value = e.localizedMessage ?: "Xeta baş verdi"
            }
        }
    }
}