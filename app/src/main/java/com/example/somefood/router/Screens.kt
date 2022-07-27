package com.example.somefood.ui

import com.example.somefood.ui.FavoriteFood.FavoriteFoodFragment
import com.example.somefood.ui.Registration.RegistrationFragment
import com.example.somefood.ui.detailFood.DetailFoodFragment
import com.example.somefood.ui.helloScreen.HelloScreenFragment
import com.example.somefood.ui.productListClient.ProductListClientFragment
import com.example.somefood.ui.signIn.SignInFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class Screens {

    fun routeToHelloScreenFragment() = FragmentScreen{ HelloScreenFragment() }
    fun openSignIn() = FragmentScreen{ SignInFragment() }
    fun openRegistration() = FragmentScreen{ RegistrationFragment() }
    fun routeToProductList(userID: Int) = FragmentScreen{ ProductListClientFragment.newInstance(userID) }
    fun routeToFavorite() = FragmentScreen{ FavoriteFoodFragment() }
    fun routeToDetail() = FragmentScreen{ DetailFoodFragment() }
}