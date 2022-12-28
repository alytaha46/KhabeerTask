package com.example.khabeertask.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.khabeertask.network.DataTransfareObject.LoginBody
import com.example.khabeertask.network.Network
import com.example.khabeertask.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

enum class LoginLoadingStatus { LOADING, DONE }
enum class ErrorType { MOBILE, PASSWORD, API, NONE }

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
        viewModelScope.launch {
            try {
                val repository = Repository()
                val loginBody = LoginBody(mobileNumber,password.toInt())
                repository.loginAndPayrollAndCache(loginBody)
                _LoadingStatus.value = LoginLoadingStatus.DONE
            } catch (e: Exception) {
                Timber.e(""+e.message)
                _LoadingStatus.value = LoginLoadingStatus.DONE
                _errorType.value = ErrorType.API
            }
        }

    }
}