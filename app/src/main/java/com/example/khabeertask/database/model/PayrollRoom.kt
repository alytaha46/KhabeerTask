package com.example.khabeertask.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.khabeertask.model.Payroll
import timber.log.Timber
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

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
    val dateFormat = SimpleDateFormat("MM/yyyy", Locale.ENGLISH).parse(date)
    val dateAr = SimpleDateFormat("MMMM,yyyy", Locale.getDefault()).format(dateFormat)
    return Payroll(
        name,
        jobName,
        dateAr,
        basicSalaryString,
        Math.round(basicSalary * 10.0) / 10.0,
        bonusSalaryString,
        Math.round(bonusSalary * 10.0) / 10.0,
        Math.round(claimingSalary * 10.0) / 10.0,
        deductionString,
        Math.round(deduction * 10.0) / 10.0,
        Math.round(netSalary * 10.0) / 10.0
    )
}