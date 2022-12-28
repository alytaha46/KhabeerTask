package com.example.khabeertask.repository

import com.example.khabeertask.network.DataTransfareObject.LoginBody
import com.example.khabeertask.network.DataTransfareObject.LoginResponse
import com.example.khabeertask.network.DataTransfareObject.PayrollResponse
import com.example.khabeertask.network.DataTransfareObject.asDatabaseModel
import com.example.khabeertask.network.Network
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class Repository {
    suspend fun login(loginBody: LoginBody): LoginResponse {
        val loginResponse = withContext(Dispatchers.IO) {
            return@withContext Network.networkCall.login(loginBody).await()
        }
        return loginResponse
    }

    suspend fun getPayroll(token: String): PayrollResponse {
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
            Timber.e("" + payrollResponse.asDatabaseModel(loginResponse.token ?: ""))
            //caching
        }
    }
}