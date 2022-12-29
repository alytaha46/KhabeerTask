package com.example.khabeertask.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.khabeertask.model.Payroll
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

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
    val dateFormat:Date = SimpleDateFormat("MM/yyyy", Locale.ENGLISH).parse(date) as Date
    val dateAr = SimpleDateFormat("MMMM,yyyy", Locale.getDefault()).format(dateFormat)
    return Payroll(
        name,
        jobName,
        dateAr,
        basicSalaryString,
        (basicSalary * 10.0).roundToInt() / 10.0,
        bonusSalaryString,
        (bonusSalary * 10.0).roundToInt() / 10.0,
        (claimingSalary * 10.0).roundToInt() / 10.0,
        deductionString,
        (deduction * 10.0).roundToInt() / 10.0,
        (netSalary * 10.0).roundToInt() / 10.0
    )
}