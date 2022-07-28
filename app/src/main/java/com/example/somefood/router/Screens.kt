package com.example.somefood.ui

import com.example.somefood.data.model.ProductListModel
import com.example.somefood.data.model.UserModel
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
    fun routeToProductList(userID: UserModel) = FragmentScreen{ ProductListClientFragment.newInstance(userID) }
    fun routeToDetail(model: ProductListModel) = FragmentScreen{ DetailFoodFragment.newInstance(model) }
    fun routeToFavorite(userID: Int) = FragmentScreen{ FavoriteFoodFragment.newInstance(userID) }
}