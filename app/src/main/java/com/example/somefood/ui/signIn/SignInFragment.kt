package com.example.somefood.ui.signIn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.somefood.R
import com.example.somefood.databinding.FragmentHelloScreenBinding
import com.example.somefood.databinding.FragmentSignInBinding
import com.example.somefood.ui.helloScreen.HelloScreenViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private val binding: FragmentSignInBinding by viewBinding()
    private val viewModel: SignInViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "Авторизация"



        binding.buttonSignInAccounts.setOnClickListener {
            val email = binding.editEmail.text
            val password = binding.editPassword.text
            viewModel.checkUser(email = email.toString(), password = password.toString())


            // Логика проверки на соответствие в базе данных
            // Если соответсвует открыть фрагмент со списком
            // если нет, то открыть форму регистрации
            //viewModel.routeToProductList()
        }
    }


}