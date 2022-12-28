package com.example.khabeertask.network.DataTransfareObject

import androidx.compose.ui.unit.Dp
import com.example.khabeertask.database.model.PayrollRoom
import com.squareup.moshi.Json
import timber.log.Timber

data class PayrollResponse(

    @Json(name = "Payroll")
    val payroll: Payroll? = null,

    @Json(name = "EnglishMessage")
    val englishMessage: String? = null,

    @Json(name = "ArabicMessage")
    val arabicMessage: String? = null,

    @Json(name = "Activation")
    val activation: Boolean? = null,

    @Json(name = "Success")
    val success: Boolean? = null
)

data class EmployeeItem(

    @Json(name = "CONTRACTSTDATE")
    val cONTRACTSTDATE: String? = null,

    @Json(name = "SAL_VALUE_NET")
    val sALVALUENET: Any? = null,

    @Json(name = "SEC_NAME_AR")
    val sECNAMEAR: String? = null,

    @Json(name = "CUSTOM_ID")
    val cUSTOMID: Any? = null,

    @Json(name = "SAL_VALUE_D")
    val sALVALUED: Any? = null,

    @Json(name = "FRACTIONNAME_AR")
    val fRACTIONNAMEAR: String? = null,

    @Json(name = "EMP_PIC")
    val eMPPIC: Any? = null,

    @Json(name = "SEC_NAME_EN")
    val sECNAMEEN: String? = null,

    @Json(name = "JOBCODE")
    val jOBCODE: Any? = null,

    @Json(name = "SAL_VALUE_A")
    val sALVALUEA: Any? = null,

    @Json(name = "COMP_DESC_D_EN")
    val cOMPDESCDEN: String? = null,

    @Json(name = "SAL_COMP_CODE_D")
    val sALCOMPCODED: Any? = null,

    @Json(name = "CURRSYMBOL_EN")
    val cURRSYMBOLEN: String? = null,

    @Json(name = "COMP_DESC_D_AR")
    val cOMPDESCDAR: String? = null,

    @Json(name = "MAR_STATUS_AR")
    val mARSTATUSAR: String? = null,

    @Json(name = "SAL_COMP_CODE_A")
    val sALCOMPCODEA: Any? = null,

    @Json(name = "CURRSYMBOL_AR")
    val cURRSYMBOLAR: String? = null,

    @Json(name = "STATUS_EN")
    val sTATUSEN: String? = null,

    @Json(name = "ATM_ACCOUNT")
    val aTMACCOUNT: Any? = null,

    @Json(name = "FRACTIONNAME_EN")
    val fRACTIONNAMEEN: String? = null,

    @Json(name = "STATUSNAME_AR")
    val sTATUSNAMEAR: String? = null,

    @Json(name = "MAR_STATUS_EN")
    val mARSTATUSEN: String? = null,

    @Json(name = "STATUS_AR")
    val sTATUSAR: String? = null,

    @Json(name = "EMP_GENDUR")
    val eMPGENDUR: String? = null,

    @Json(name = "EMP_DATA_AR")
    val eMPDATAAR: String? = null,

    @Json(name = "COMP_DESC_A_EN")
    val cOMPDESCAEN: String? = null,

    @Json(name = "STATUSNAME_EN")
    val sTATUSNAMEEN: String? = null,

    @Json(name = "EMP_ID")
    val eMPID: Int? = null,

    @Json(name = "JOBNAME_EN")
    val jOBNAMEEN: String? = null,

    @Json(name = "EMP_DATA_EN")
    val eMPDATAEN: String? = null,

    @Json(name = "COMP_DESC_A_AR")
    val cOMPDESCAAR: String? = null,

    @Json(name = "JOBNAME_AR")
    val jOBNAMEAR: String? = null
)

data class DeductionItem(

    @Json(name = "EMP_ID")
    val eMPID: Int? = null,

    @Json(name = "SAL_COMP_CODE")
    val sALCOMPCODE: Int? = null,

    @Json(name = "COMP_DESC_EN")
    val cOMPDESCEN: String? = null,

    @Json(name = "SAL_VALUE")
    val sALVALUE: Any? = null,

    @Json(name = "COMP_DESC_AR")
    val cOMPDESCAR: String? = null,

    @Json(name = "SAL_COMP_TYPE")
    val sALCOMPTYPE: Int? = null
)

data class AllowencesItem(

    @Json(name = "EMP_ID")
    val eMPID: Int? = null,

    @Json(name = "SAL_COMP_CODE")
    val sALCOMPCODE: Int? = null,

    @Json(name = "COMP_DESC_EN")
    val cOMPDESCEN: String? = null,

    @Json(name = "SAL_VALUE")
    val sALVALUE: Any? = null,

    @Json(name = "COMP_DESC_AR")
    val cOMPDESCAR: String? = null,

    @Json(name = "SAL_COMP_TYPE")
    val sALCOMPTYPE: Int? = null
)

data class Payroll(

    @Json(name = "Employee")
    val employee: List<EmployeeItem?>? = null,

    @Json(name = "Allowences")
    val allowences: List<AllowencesItem?>? = null,

    @Json(name = "Deduction")
    val deduction: List<DeductionItem?>? = null,

    @Json(name = "Date")
    val date: String? = null
)

fun PayrollResponse.asDatabaseModel(token: String): PayrollRoom {
    val name: String = payroll?.employee?.get(0)?.eMPDATAAR ?: ""
    val jobName: String = payroll?.employee?.get(0)?.jOBNAMEAR ?: ""
    val date: String = payroll?.date ?: ""
    val basicSalaryString: String = payroll?.allowences?.get(0)?.cOMPDESCAR ?: ""
    val basicSalary: Double = (payroll?.allowences?.get(0)?.sALVALUE ?: 0.0) as Double
    val bonusSalaryString: String = payroll?.allowences?.get(1)?.cOMPDESCAR ?: ""
    val bonusSalary: Double = (payroll?.allowences?.get(1)?.sALVALUE ?: 0.0) as Double
    val claimingSalary: Double = basicSalary + bonusSalary
    val deductionString: String = payroll?.deduction?.get(0)?.cOMPDESCAR ?: ""
    val deduction: Double = (payroll?.deduction?.get(0)?.sALVALUE ?: 0.0) as Double
    val netSalary: Double = claimingSalary - deduction

    return PayrollRoom(
        token,
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
