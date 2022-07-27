package com.example.somefood.di

import androidx.room.Room
import com.example.somefood.data.room.Repository
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

        // Сингл ДАО и БД
        single { Room.databaseBuilder(get(), UserDataBase::class.java, "user_table").build() }
        single{ get<UserDataBase>().somethingDao()}

        // Сингл репозитория для работы с БД пользователей
        single { Repository(get()) }

        viewModel { MainViewModel(get(), get()) }
        viewModel { HelloScreenViewModel(get()) }
        viewModel { SignInViewModel(get(), get()) }
        viewModel { RegistrationViewModel(get(), get()) }
        viewModel { ProductListClientViewModel(get()) }
    }
