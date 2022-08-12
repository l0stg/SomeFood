package com.example.somefood.di

import androidx.room.Room
import com.example.somefood.data.room.provider.*
import com.example.somefood.data.room.repository.OrderRepository
import com.example.somefood.data.room.repository.RepositoryFavorite
import com.example.somefood.data.room.repository.RepositoryFood
import com.example.somefood.data.room.repository.RepositoryUser
import com.example.somefood.ui.FavoriteFood.FavoriteViewModel
import com.example.somefood.ui.Registration.RegistrationViewModel
import com.example.somefood.ui.bottomSheetFragment.DialogViewModel
import com.example.somefood.ui.orderList.OrderFragmentViewModel
import com.example.somefood.ui.helloScreen.HelloScreenViewModel
import com.example.somefood.ui.mainActivite.MainViewModel
import com.example.somefood.ui.orderBasket.OrderBasketViewModel
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
        single { Room.databaseBuilder(get(), SomeFoodDataBase::class.java, "food_db").build() }
        single { get<SomeFoodDataBase>().foodDao() }
        single { get<SomeFoodDataBase>().somethingDao() }
        single { get<SomeFoodDataBase>().orderDao() }
        single { get<SomeFoodDataBase>().favoriteDao() }

        // Сингл репозитория для работы с БД пользователей
        single { RepositoryUser(get(), get()) }
        single { RepositoryFood(get()) }
        single { RepositoryFavorite(get()) }
        single { OrderRepository(get())}

        viewModel { DialogViewModel(get(), get())}
        viewModel { MainViewModel(get(), get(), get()) }
        viewModel { HelloScreenViewModel(get()) }
        viewModel { SignInViewModel(get(), get()) }
        viewModel { RegistrationViewModel(get(), get()) }
        viewModel { ProductListClientViewModel(get(), get(), get(), get()) }
        viewModel { FavoriteViewModel(get(), get(), get(), get())}
        viewModel {OrderFragmentViewModel(get(), get(), get())}
        viewModel { OrderBasketViewModel(get(), get())}


    }
