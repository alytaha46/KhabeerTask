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
    val database = getDatabase(application)
    val repository = Repository(database)
    val isDatabaseEmpty = repository.isDatabaseEmpty
    val mobileNumber = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val _errorType = MutableLiveData<ErrorType>(ErrorType.NONE)
    val errorType:LiveData<ErrorType> get() = _errorType

    private val _moveToPayrollFragment = MutableLiveData<Boolean>(false)
    val moveToPayrollFragment:LiveData<Boolean> get() = _moveToPayrollFragment

    private val _LoadingStatus = MutableLiveData<LoginLoadingStatus>(LoginLoadingStatus.LOADING)
    val LoadingStatus:LiveData<LoginLoadingStatus> get() = _LoadingStatus

    fun loginButton() {
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
                moveToPayrollFragment()
            } catch (e: Exception) {
                Timber.e("" + e.message)
                _LoadingStatus.value = LoginLoadingStatus.DONE
                _errorType.value = ErrorType.API
            }
        }
    }

    fun moveToPayrollFragment() {
        _moveToPayrollFragment.value = true
    }

    fun onNavigateDone() {
        _moveToPayrollFragment.value = false
    }

    fun setStatusToDone(){
        _LoadingStatus.value = LoginLoadingStatus.DONE
    }
}