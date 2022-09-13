package com.example.somefood.ui

import com.example.somefood.ui.FavoriteFood.FavoriteFoodFragment
import com.example.somefood.ui.Registration.RegistrationFragment
import com.example.somefood.ui.detailFood.DetailFoodFragment
import com.example.somefood.ui.habitAchiev.AchievmentFragment
import com.example.somefood.ui.helloScreen.HelloScreenFragment
import com.example.somefood.ui.historyOrderFragment.HistoryOrderFragment
import com.example.somefood.ui.orderBasket.OrderBasketFragment
import com.example.somefood.ui.orderByCreator.OrderByCreatorFragment
import com.example.somefood.ui.orderList.CreatorListFragment
import com.example.somefood.ui.productListClient.ProductListClientFragment
import com.example.somefood.ui.profile.ProfileFragment
import com.example.somefood.ui.signIn.SignInFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class Screens {

    fun routeToAch() = FragmentScreen { AchievmentFragment() }
    fun routeToHelloScreenFragment() = FragmentScreen { HelloScreenFragment() }
    fun openSignIn() = FragmentScreen { SignInFragment() }
    fun openRegistration() = FragmentScreen { RegistrationFragment() }
    fun routeToProductList() = FragmentScreen { ProductListClientFragment.newInstance() }
    fun routeToDetail(foodId: Int) =
        FragmentScreen { DetailFoodFragment.newInstance(foodId) }
    fun routeToFavorite() = FragmentScreen { FavoriteFoodFragment.newInstance() }
    fun routeToCreatorList() = FragmentScreen { CreatorListFragment() }
    fun routeToBascet() = FragmentScreen { OrderBasketFragment.newInstance() }
    fun routeToOrderByCreator() = FragmentScreen { OrderByCreatorFragment() }
    fun routeToProfile() = FragmentScreen { ProfileFragment() }
    fun routeToHistoryOrder() = FragmentScreen { HistoryOrderFragment.newInstance() }
}