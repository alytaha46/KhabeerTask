package com.example.khabeertask.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.khabeertask.database.getDatabase
import com.example.khabeertask.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PayrollViewModel(application: Application) : AndroidViewModel(application) {
    private val database = getDatabase(application)
    val repository = Repository(database)
    val payroll = repository.payroll

    private val _LoadingStatus = MutableLiveData<LoginLoadingStatus>(LoginLoadingStatus.LOADING)
    val LoadingStatus: LiveData<LoginLoadingStatus> get() = _LoadingStatus

    private val _moveToLoginFragment = MutableLiveData<Boolean>(false)
    val moveToLoginFragment: LiveData<Boolean> get() = _moveToLoginFragment
    fun logout() {
        viewModelScope.launch(Dispatchers.Main) {
            _LoadingStatus.value = LoginLoadingStatus.LOADING
            withContext(Dispatchers.IO)
            {
                database.payrollDao.clearPayroll()
            }
            moveToLoginFragment()
        }
    }

    fun moveToLoginFragment() {
        _moveToLoginFragment.value = true
    }

    fun onNavigateDone() {
        _moveToLoginFragment.value = false
    }

    fun doneLoading() {
        _LoadingStatus.value = LoginLoadingStatus.DONE
    }
}