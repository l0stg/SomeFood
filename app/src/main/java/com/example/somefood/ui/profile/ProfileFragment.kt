package com.example.somefood.ui.profile

import android.Manifest
import android.R.attr
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.core.content.ContextCompat.checkSelfPermission
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
            addNewProfileImageButton.setOnClickListener {
                pickImageFromGallery()
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
                        orderIntComplit.text = it.orderByClient.toString()
                        orderIntPick.text = it.orderByCreator.toString()
                        starForCreator.text = String.format("%.1f", it.ratingByCreator)
                        starForClient.text = String.format("%.1f",it.ratingByClient)
                        starMidlle.text = String.format("%.1f", listOf(it.ratingByClient, it.ratingByCreator).average())
                        when(it.types){
                            UserTypes.USER -> switchTypesInProfile.isChecked = false
                            UserTypes.CREATOR -> switchTypesInProfile.isChecked = true
                        }
                    }
                }
            }
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    companion object {
        private val IMAGE_PICK_CODE = 1000;
        }

    //handle requested permission result

    //handle result of picked image
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE){
            binding.profilePhoto.setImageURI(data?.data)

        }
    }
}
