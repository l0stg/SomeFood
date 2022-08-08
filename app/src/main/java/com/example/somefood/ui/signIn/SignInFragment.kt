package com.example.somefood.ui.signIn

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.somefood.R
import com.example.somefood.data.MyPreference
import com.example.somefood.databinding.FragmentSignInBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private val binding: FragmentSignInBinding by viewBinding()
    private val viewModel: SignInViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = R.string.auth.toString()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.status.collect {
                    if (it) {
                        Toast.makeText(activity, "Неверный логин/пароль", Toast.LENGTH_SHORT).show()
                        viewModel.status.value = false
                    }
                }
            }
        }

        binding.buttonSignInAccounts.setOnClickListener {
            signInButton()
        }
    }

    private fun signInButton() {
        viewModel.checkUser(
            email = binding.editEmail.editText?.text.toString(),
            password = binding.editPassword.editText?.text.toString())
    }
}