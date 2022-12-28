package com.example.khabeertask.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

enum class LoginLoadingStatus { LOADING, ERROR, DONE }
enum class ErrorType { MOBILE, PASSWORD, API , NONE }

class LoginViewModel : ViewModel() {
    val mobileNumber = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val _errorType = MutableLiveData<ErrorType>(ErrorType.NONE)
    val errorType get() = _errorType

    private val _LoadingStatus = MutableLiveData<LoginLoadingStatus>(LoginLoadingStatus.DONE)
    val LoadingStatus get() = _LoadingStatus

    fun login() {
        val mobileNumber: String = mobileNumber.value.toString()
        val password: String = password.value.toString()
        if (mobileNumber.length < 10) {
            _errorType.value = ErrorType.MOBILE
            return
        } else if (password.length < 5) {
            _errorType.value = ErrorType.PASSWORD
            return
        }
        _LoadingStatus.value = LoginLoadingStatus.LOADING
        _errorType.value = ErrorType.NONE

    }
}