package com.example.khabeertask.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.khabeertask.database.AppDatabase
import com.example.khabeertask.database.model.PayrollRoom
import com.example.khabeertask.database.model.asDomainModel
import com.example.khabeertask.model.Payroll
import com.example.khabeertask.network.dataTransfareObject.LoginBody
import com.example.khabeertask.network.dataTransfareObject.LoginResponse
import com.example.khabeertask.network.dataTransfareObject.PayrollResponse
import com.example.khabeertask.network.dataTransfareObject.asDatabaseModel
import com.example.khabeertask.network.Network
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(private val database: AppDatabase) {
    val payroll: LiveData<Payroll?> = Transformations.map(database.payrollDao.getPayroll())
    {
        it?.asDomainModel()
    }
    val isDatabaseEmpty: LiveData<Boolean> = database.payrollDao.isEmpty()

    private suspend fun login(loginBody: LoginBody): LoginResponse {
        val loginResponse = withContext(Dispatchers.IO) {
            return@withContext Network.networkCall.login(loginBody).await()
        }
        return loginResponse
    }

    private suspend fun getPayroll(token: String): PayrollResponse {
        val payrollResponse = withContext(Dispatchers.IO)
        {
            return@withContext Network.networkCall.getPayroll("Bearer $token").await()
        }
        return payrollResponse
    }

    suspend fun loginAndPayrollAndCache(loginBody: LoginBody) {
        val loginResponse = login(loginBody)
        if (loginResponse.success != true) {
            throw Exception("Login Failed")
        } else {
            val payrollResponse = getPayroll(loginResponse.token ?: "")
            val payrollRoom = payrollResponse.asDatabaseModel(loginResponse.token ?: "")
            insertPayrollIntoDatabase(payrollRoom)
        }
    }

    private suspend fun insertPayrollIntoDatabase(payrollRoom: PayrollRoom) {
        database.payrollDao.insertPayroll(payrollRoom)
    }
}