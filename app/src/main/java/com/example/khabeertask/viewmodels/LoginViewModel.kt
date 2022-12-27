package com.example.khabeertask.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class LoginViewModel : ViewModel() {
    val mobileNumber = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val _errorType = MutableLiveData<String>("")
    val errorType get() = _errorType

    fun login() {
        val mobileNumber: String = mobileNumber.value.toString()
        val password: String = password.value.toString()
        if (mobileNumber.length < 10) {
            _errorType.value = "Mobile"
            return
        } else if (password.length < 5) {
            _errorType.value = "Password"
            return
        }
        _errorType.value = ""

    }
}