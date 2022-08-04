package com.example.somefood.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module

val preferenceModule = module {
    single { provideSettingsPreferences(androidApplication()) }
}

private const val PREFERENCES_FILE_KEY = "preferences"

private fun provideSettingsPreferences(app: Application): SharedPreferences =
    app.getSharedPreferences(PREFERENCES_FILE_KEY, Context.MODE_PRIVATE)

