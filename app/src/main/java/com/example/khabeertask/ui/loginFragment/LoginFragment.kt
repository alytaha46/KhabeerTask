package com.example.khabeertask.ui.loginFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.khabeertask.R
import com.example.khabeertask.databinding.FragmentLoginBinding
import com.example.khabeertask.viewmodels.LoginViewModel

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.moveToPayrollFragment.observe(viewLifecycleOwner, Observer {
            if (it) {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToPayrollFragment())
                viewModel.onNavigateDone()
            }
        })
        viewModel.isDatabaseEmpty.observe(viewLifecycleOwner, Observer {
            if (!it)
            {
                viewModel.moveToPayrollFragment()
            }
            else
            {
                viewModel.setStatusToDone()
            }
        })

        return binding.root
    }
}
