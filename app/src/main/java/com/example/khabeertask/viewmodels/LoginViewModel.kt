package com.example.khabeertask.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.khabeertask.database.getDatabase
import com.example.khabeertask.network.DataTransfareObject.LoginBody
import com.example.khabeertask.network.Network
import com.example.khabeertask.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

enum class LoginLoadingStatus { LOADING, DONE }
enum class ErrorType { MOBILE, PASSWORD, API, NONE }

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val database = getDatabase(application)
    val repository = Repository(database)
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
                val loginBody = LoginBody(mobileNumber, password.toInt())
                repository.loginAndPayrollAndCache(loginBody)
                _LoadingStatus.value = LoginLoadingStatus.DONE
                //move to second fragment
            } catch (e: Exception) {
                Timber.e("" + e.message)
                _LoadingStatus.value = LoginLoadingStatus.DONE
                _errorType.value = ErrorType.API
            }
        }
    }
}