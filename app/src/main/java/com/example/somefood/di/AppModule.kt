package com.example.somefood.di

import androidx.room.Room
import com.example.somefood.data.room.provider.FoodDataBase
import com.example.somefood.data.room.provider.UserDataBase
import com.example.somefood.data.room.repository.RepositoryFood
import com.example.somefood.data.room.repository.RepositoryUser
import com.example.somefood.ui.FavoriteFood.FavoriteViewModel
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

        // Сингл ДАО и БД User
        single { Room.databaseBuilder(get(), UserDataBase::class.java, "user_table").build() }
        single{ get<UserDataBase>().somethingDao()}

        // Сингл ДАО и БД Food
        single { Room.databaseBuilder(get(), FoodDataBase::class.java, "favorite_table").build() }
        single { get<FoodDataBase>().favoriteDao() }

        // Сингл репозитория для работы с БД пользователей
        single { RepositoryUser(get()) }
        single { RepositoryFood(get()) }

        viewModel { MainViewModel(get(), get()) }
        viewModel { HelloScreenViewModel(get()) }
        viewModel { SignInViewModel(get(), get()) }
        viewModel { RegistrationViewModel(get(), get()) }
        viewModel { ProductListClientViewModel(get(), get(), get()) }
        viewModel { FavoriteViewModel(get())}
    }
