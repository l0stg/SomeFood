package com.example.somefood.ui.signIn

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.somefood.R
import com.example.somefood.databinding.FragmentSignInBinding
import com.example.somefood.ui.Event.onEachEvent
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private val binding: FragmentSignInBinding by viewBinding()
    private val viewModel: SignInViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = getString(R.string.auth)

        viewModel.status.filterNotNull().onEachEvent {
            if (it)
                Snackbar.make(
                    binding.root,
                    getString(R.string.doubleRegistr),
                    Snackbar.LENGTH_SHORT
                ).show()
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        binding.buttonSignInAccounts.setOnClickListener {
            signInButton()
        }
    }

    private fun signInButton() {
        viewModel.checkUser(
            email = binding.editEmail.editText?.text.toString(),
            password = binding.editPassword.editText?.text.toString()
        )
    }
}