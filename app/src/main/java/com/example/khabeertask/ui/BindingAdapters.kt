package com.example.khabeertask.ui

import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.khabeertask.R
import com.example.khabeertask.ui.payrollFragment.CustomView
import com.example.khabeertask.viewmodels.ErrorType
import com.example.khabeertask.viewmodels.LoginLoadingStatus
import timber.log.Timber

@BindingAdapter("setErrorText")
fun setErrorText(textView: TextView, error: ErrorType) {
    if (error == ErrorType.MOBILE) {
        textView.setText(R.string.mobile_error)
    } else if (error == ErrorType.PASSWORD) {
        textView.setText(R.string.password_error)
    } else if (error == ErrorType.API) {
        textView.setText(R.string.api_error)
    } else {
        textView.text = ""
    }
}

@BindingAdapter("loginLoading")
fun loginLoading(progressBar: ProgressBar, loginLoadingStatus: LoginLoadingStatus) {
    progressBar.visibility =
        if (loginLoadingStatus == LoginLoadingStatus.LOADING) View.VISIBLE else View.GONE
}

@BindingAdapter("disable")
fun disable(view: View, loginLoadingStatus: LoginLoadingStatus) {
    view.isEnabled = loginLoadingStatus != LoginLoadingStatus.LOADING
}

@BindingAdapter("addClaiming", "addDeduction")
fun addData(view: CustomView, claimingSalary: Double, deduction: Double) {
    view.claimingSalary = claimingSalary
    view.deduction = deduction
    view.total = claimingSalary + deduction
    view.claimingSalaryPercent = claimingSalary/view.total
    view.deductionPercent = deduction/view.total
    view.claimingSalaryEndAngle = view.claimingSalaryPercent*360
    view.deductionEndAngle = view.deductionPercent*360
}