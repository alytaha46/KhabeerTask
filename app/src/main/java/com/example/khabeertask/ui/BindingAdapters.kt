package com.example.khabeertask.ui

import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.khabeertask.R
import com.example.khabeertask.viewmodels.ErrorType
import com.example.khabeertask.viewmodels.LoginLoadingStatus

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