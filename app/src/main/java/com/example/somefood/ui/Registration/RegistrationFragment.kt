package com.example.somefood.ui.Registration

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.somefood.R
import com.example.somefood.data.model.UserTypes
import com.example.somefood.databinding.FragmentRegistrationBinding
import com.example.somefood.ui.Event.onEachEvent
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private val binding: FragmentRegistrationBinding by viewBinding()
    private val viewModel: RegistrationViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = getString(R.string.registr)
        var types = UserTypes.USER

        viewModel.doubleRegistr.filterNotNull().onEachEvent {
            if (it)
                Snackbar.make(
                    binding.root,
                    getString(R.string.doubleRegistr),
                    Snackbar.LENGTH_SHORT
                ).show()
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        binding.switchTypes.setOnCheckedChangeListener { _, isChecked ->
            types = if (isChecked) {
                UserTypes.CREATOR
            } else {
                UserTypes.USER
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