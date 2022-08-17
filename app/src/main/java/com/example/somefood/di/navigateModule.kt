package com.example.somefood.di

import com.github.terrakok.cicerone.Cicerone
import org.koin.dsl.module


val navigateModule = module {

    val cicerone = Cicerone.create()
    single { cicerone.router }
    single { cicerone.getNavigatorHolder() }
}
