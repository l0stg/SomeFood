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
import com.example.somefood.ui.detailFood.DetailFoodViewModel
import com.example.somefood.ui.orderList.OrderFragmentViewModel
import com.example.somefood.ui.helloScreen.HelloScreenViewModel
import com.example.somefood.ui.mainActivite.MainViewModel
import com.example.somefood.ui.orderBasket.OrderBasketViewModel
import com.example.somefood.ui.productListClient.ProductListClientViewModel
import com.example.somefood.ui.signIn.SignInViewModel
import com.github.terrakok.cicerone.Cicerone
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module



    val navigateModule = module {

        val cicerone = Cicerone.create()
        // Сингл для Cicerone
        single{cicerone.router}
        single{cicerone.getNavigatorHolder()}
    }
