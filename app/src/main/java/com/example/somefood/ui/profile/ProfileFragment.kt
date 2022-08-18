package com.example.somefood.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.somefood.R
import com.example.somefood.data.model.UserTypes
import com.example.somefood.databinding.FragmentProfileBinding
import com.example.somefood.ui.BackButtonListener
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment(R.layout.fragment_profile), BackButtonListener {


    private val viewModel: ProfileViewModel by viewModel()
    private val binding: FragmentProfileBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userProfile.collect {
                    binding.emailUser.text = it.eMail
                    binding.userDescription.text = it.description
                    when(it.types){
                        UserTypes.USER -> binding.switchTypesInProfile.isChecked = false
                        UserTypes.CREATOR -> binding.switchTypesInProfile.isChecked = true
                    }
                }
            }
        }

        binding.switchTypesInProfile.setOnCheckedChangeListener { _, isChecked ->
            val types: UserTypes
            when (isChecked) {
                true -> types = UserTypes.CREATOR
                false -> types = UserTypes.USER
            }
            viewModel.switchTypes(types)
        }
    }
    override fun onBackPressed(): Boolean {
        viewModel.routeToMainScreen()
        return true
    }
}
