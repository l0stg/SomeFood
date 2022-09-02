package com.example.somefood.ui.profile

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.somefood.R
import com.example.somefood.data.model.UserTypes
import com.example.somefood.databinding.FragmentProfileBinding
import com.example.somefood.ui.BackButtonListener
import com.example.somefood.ui.LoadingDialog
import com.example.somefood.ui.PhotoPicker
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProfileFragment : Fragment(R.layout.fragment_profile), BackButtonListener {


    private var photoPicker: PhotoPicker? = null
    private val viewModel: ProfileViewModel by viewModel()
    private val binding: FragmentProfileBinding by viewBinding()
    private var loading: LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loading = LoadingDialog(requireActivity())
        loading?.startLoading()
        photoPicker =
            PhotoPicker(requireActivity().activityResultRegistry) { uri ->
                viewModel.savePhoto(uri)
            }
    }

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
                        it.showKeyboard(userDescription)
                        userDescription.setSelection(userDescription.length())

                    }
                }
            }
            addNewProfileImageButton.setOnClickListener {
                photoPicker?.pickPhoto()
            }
            switchTypesInProfile.setOnCheckedChangeListener { _, isChecked ->
                viewModel.goSwitchType(isChecked)
            }
        }
    }

    private fun View.showKeyboard(editText: EditText) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
    }

    override fun onBackPressed(): Boolean {
        viewModel.routeToMainScreen()
        return true
    }

    private fun updateProfileView(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userProfile.filterNotNull().collect {
                    with(binding) {
                        emailUser.text = it.email
                        userDescription.setText(it.description)
                        orderIntComplit.text = it.ordersAsCreator.toString()
                        orderIntPick.text = it.ordersAsClient.toString()
                        starForCreator.text = String.format("%.1f", it.starForCreator)
                        starForClient.text = String.format("%.1f", it.starForClient)
                        starMidlle.text = String.format(
                            "%.1f",
                            listOf(it.starForClient, it.starForCreator).average()
                        )
                        cvHistoryOrder.setOnClickListener {
                            viewModel.routeToHistoryOrder()
                        }
                        Glide
                            .with(profilePhoto.context)
                            .asBitmap()
                            .load(it.photoProfile)
                            .centerCrop()
                            .placeholder(R.drawable.ic_launcher_foreground)
                            .into(profilePhoto)
                        when (it.types) {
                            UserTypes.USER -> switchTypesInProfile.isChecked = false
                            UserTypes.CREATOR -> switchTypesInProfile.isChecked = true
                        }
                    }
                    loading?.isDismiss()
                }
            }
        }
    }
}
