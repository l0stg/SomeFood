package com.example.somefood.ui.habitAchiev

import androidx.lifecycle.ViewModel
import com.example.somefood.ui.Screens
import com.github.terrakok.cicerone.Router

class AchievmentViewModel(
    private val router: Router
) : ViewModel() {

    fun routeToBack() = router.backTo(Screens().routeToProfile())
}