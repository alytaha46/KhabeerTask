package com.example.khabeertask.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.khabeertask.database.model.PayrollRoom

@Dao
interface PayrollDao {
    @Query("select * from payrollroom")
    fun getPayroll(): LiveData<PayrollRoom>

    @Query("SELECT (SELECT COUNT(*) FROM payrollroom) == 0")
    fun isEmpty(): LiveData<Boolean>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPayroll(payrollRoom: PayrollRoom)

    @Query("Delete from payrollroom")
    suspend fun clearPayroll()
}