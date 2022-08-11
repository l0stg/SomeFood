package com.example.somefood.ui.Registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.somefood.R
import com.example.somefood.data.model.UserTypes
import com.example.somefood.databinding.FragmentRegistrationBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private val binding: FragmentRegistrationBinding by viewBinding()
    private val viewModel: RegistrationViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = getString(R.string.registr)
        var types = UserTypes.USER

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.statusRegistration.collect {
                    if (it)
                        Snackbar.make(binding.root,getString(R.string.doubleRegistr),Snackbar.LENGTH_SHORT).show()
                }
            }
        }

        binding.switchTypes.setOnCheckedChangeListener { _, isChecked ->
            when (isChecked) {
                true -> types = UserTypes.CREATOR
                false -> types = UserTypes.USER
            }
        }

        binding.buttonNewRegistration.setOnClickListener {
            registrationButton(types)
        }
    }


    private fun registrationButton(types: UserTypes) {
        viewModel.addUser(
            email = binding.editEmailRegistration.editText?.text.toString(),
            password = binding.editPasswordRegistration.editText?.text.toString(),
            types = types
        )
    }
}