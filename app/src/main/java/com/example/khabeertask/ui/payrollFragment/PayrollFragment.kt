package com.example.khabeertask.ui.payrollFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.khabeertask.R
import com.example.khabeertask.databinding.FragmentPayrollBinding
import com.example.khabeertask.viewmodels.PayrollViewModel


class PayrollFragment : Fragment() {
    private lateinit var viewModel: PayrollViewModel
    private lateinit var binding: FragmentPayrollBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[PayrollViewModel::class.java]
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_payroll, container, false)
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }


}