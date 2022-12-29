package com.example.khabeertask.ui

import android.graphics.Paint
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.khabeertask.R
import com.example.khabeertask.ui.payrollFragment.CustomView
import com.example.khabeertask.viewmodels.ErrorType
import com.example.khabeertask.viewmodels.LoginLoadingStatus

@BindingAdapter("setErrorText")
fun setErrorText(textView: TextView, error: ErrorType) {
    when (error) {
        ErrorType.MOBILE -> {
            textView.setText(R.string.mobile_error)
        }
        ErrorType.PASSWORD -> {
            textView.setText(R.string.password_error)
        }
        ErrorType.API -> {
            textView.setText(R.string.api_error)
        }
        else -> {
            textView.text = ""
        }
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

@BindingAdapter("setStrokeText")
fun setStrokeText(textView: TextView, string: String) {
    textView.text = string
    textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
}
