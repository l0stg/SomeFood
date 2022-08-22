package com.example.somefood.di

import com.example.somefood.ui.FavoriteFood.FavoriteViewModel
import com.example.somefood.ui.Registration.RegistrationViewModel
import com.example.somefood.ui.bottomSheetFragment.DialogViewModel
import com.example.somefood.ui.bottomSheetRating.BottomSheetRatingViewModel
import com.example.somefood.ui.detailFood.DetailFoodViewModel
import com.example.somefood.ui.helloScreen.HelloScreenViewModel
import com.example.somefood.ui.mainActivite.MainViewModel
import com.example.somefood.ui.orderBasket.OrderBasketViewModel
import com.example.somefood.ui.orderByCreator.OrderByCreatorViewModel
import com.example.somefood.ui.orderList.OrderFragmentViewModel
import com.example.somefood.ui.productListClient.ProductListClientViewModel
import com.example.somefood.ui.profile.ProfileViewModel
import com.example.somefood.ui.signIn.SignInViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { DialogViewModel(get(), get()) }
    viewModel { MainViewModel(get(), get(), get()) }
    viewModel { HelloScreenViewModel(get()) }
    viewModel { SignInViewModel(get(), get()) }
    viewModel { RegistrationViewModel(get(), get()) }
    viewModel { ProductListClientViewModel(get(), get(), get(), get()) }
    viewModel { FavoriteViewModel(get(), get(), get(), get()) }
    viewModel { OrderFragmentViewModel(get(), get(), get()) }
    viewModel { OrderBasketViewModel(get(), get()) }
    viewModel { DetailFoodViewModel(get(), get()) }
    viewModel { OrderByCreatorViewModel(get(), get()) }
    viewModel { ProfileViewModel (get(), get()) }
    viewModel { BottomSheetRatingViewModel(get(), get())}
}