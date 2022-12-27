package com.example.khabeertask.ui.loginFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.khabeertask.R
import com.example.khabeertask.databinding.FragmentLoginBinding
import com.example.khabeertask.viewmodels.LoginViewModel
import timber.log.Timber

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.errorType.observe(viewLifecycleOwner, Observer {
            if (it == "Mobile") {
                binding.error.setText(R.string.mobile_error)
            } else if (it == "Password") {
                binding.error.setText(R.string.password_error)
            }else
            {
                binding.error.text = ""
            }
        })
        return binding.root
    }
}
