package com.example.khabeertask.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.khabeertask.model.Payroll

@Entity
data class PayrollRoom(
    @PrimaryKey val token: String,
    val name: String,
    val jobName: String,
    val date: String,
    val basicSalaryString: String,
    val basicSalary: Double,
    val bonusSalaryString: String,
    val bonusSalary: Double,
    val claimingSalary: Double,
    val deductionString: String,
    val deduction: Double,
    val netSalary: Double
)

fun PayrollRoom.asDomainModel(): Payroll {
    return Payroll(
        name,
        jobName,
        date,
        basicSalaryString,
        basicSalary,
        bonusSalaryString,
        bonusSalary,
        claimingSalary,
        deductionString,
        deduction,
        netSalary
    )
}