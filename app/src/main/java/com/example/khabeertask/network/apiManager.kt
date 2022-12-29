package com.example.khabeertask.network

import com.example.khabeertask.network.dataTransfareObject.LoginBody
import com.example.khabeertask.network.dataTransfareObject.LoginResponse
import com.example.khabeertask.network.dataTransfareObject.PayrollResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface Api {
    @POST("LogIn")
    fun login(@Body loginBody: LoginBody): Deferred<LoginResponse>

    @GET("GetPayroll")
    fun getPayroll(@Header("Authorization") token: String): Deferred<PayrollResponse>
}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
    .connectTimeout(1, TimeUnit.MINUTES)
    .readTimeout(1, TimeUnit.MINUTES)
    .writeTimeout(1, TimeUnit.MINUTES)
    .build()

object Network {
    // Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://40.127.194.127:5656/Salamtak/")
        .client(okHttpClient)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val networkCall = retrofit.create(Api::class.java)
}