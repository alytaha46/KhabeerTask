package com.example.khabeertask.model

data class Payroll(
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
