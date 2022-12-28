package com.example.khabeertask.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.khabeertask.database.getDatabase
import com.example.khabeertask.repository.Repository

class PayrollViewModel(application: Application) :AndroidViewModel(application) {
    private val database = getDatabase(application)
    val repository = Repository(database)
    val payroll = repository.payroll
}