package com.example.somefood.ui.Registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.somefood.R
import com.example.somefood.databinding.FragmentRegistrationBinding
import com.example.somefood.databinding.FragmentSignInBinding
import com.example.somefood.ui.signIn.SignInViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private val binding: FragmentRegistrationBinding by viewBinding()
    private val viewModel: RegistrationViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "Регистрация"
        var types: Boolean? = null

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.statusRegistration.collect {
                    if (it) {
                        Toast.makeText(
                            activity,
                            "Пользователь уже зарегистрирован",
                            Toast.LENGTH_SHORT
                        ).show()
                        viewModel.statusRegistration.value = false
                    }
                }
            }
        }

        binding.switchTypes.setOnCheckedChangeListener { _, isChecked ->
            types = isChecked
        }

        binding.buttonNewRegistration.setOnClickListener {
            types?.let { it1 -> registrationButton(it1) }
        }


    }


    private fun registrationButton(types: Boolean) {
        val email = binding.editEmailRegistration.text
        val password = binding.editPasswordRegistration.text
        viewModel.addUser(email = email.toString(), password = password.toString(), types = types)
    }


}