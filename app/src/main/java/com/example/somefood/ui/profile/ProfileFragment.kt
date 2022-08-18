package com.example.somefood.ui.profile

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
        activity?.title = getString(R.string.profile)
        updateProfileView()

        with(binding){
            buttonEditDescription.setOnClickListener {
                when(userDescription.isEnabled){
                    true -> {
                        viewModel.updateDescription(userDescription.text.toString())
                        userDescription.isEnabled = false
                    }
                    false -> {
                        userDescription.isEnabled = true
                        userDescription.requestFocus()
                    }
                }
            }
            switchTypesInProfile.setOnCheckedChangeListener { _, isChecked ->
                viewModel.goSwitchType(isChecked)
            }
        }
    }

    override fun onBackPressed(): Boolean {
        viewModel.routeToMainScreen()
        return true
    }

    private fun updateProfileView(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userProfile.collect {
                    with(binding){
                        emailUser.text = it.eMail
                        userDescription.setText(it.description)
                        starForCreator.text = it.starForCreator.toString()
                        starForClient.text = it.starForClient.toString()
                        starMidlle.text = listOf(it.starForCreator, it.starForClient).average().toString()
                        when(it.types){
                            UserTypes.USER -> switchTypesInProfile.isChecked = false
                            UserTypes.CREATOR -> switchTypesInProfile.isChecked = true
                        }
                    }
                }
            }
        }
    }
}
