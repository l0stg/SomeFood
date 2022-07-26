package com.example.somefood.di

import com.example.somefood.data.room.provider.UserDataBase
import com.example.somefood.ui.Registration.RegistrationViewModel
import com.example.somefood.ui.helloScreen.HelloScreenViewModel
import com.example.somefood.ui.mainActivite.MainViewModel
import com.example.somefood.ui.productListClient.ProductListClientViewModel
import com.example.somefood.ui.signIn.SignInViewModel
import com.github.terrakok.cicerone.Cicerone
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


    val appModule = module {

        val cicerone = Cicerone.create()
        // Сингл для Cicerone
        single{cicerone.router}
        single{cicerone.getNavigatorHolder()}

        // Сингл ДАО
        single{UserDataBase.instance?.somethingDao()}

        viewModel { MainViewModel(get()) }
        viewModel { HelloScreenViewModel(get()) }
        viewModel { SignInViewModel(get()) }
        viewModel { RegistrationViewModel(get()) }
        viewModel { ProductListClientViewModel(get()) }
    }
