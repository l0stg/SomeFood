package com.example.somefood.app

import android.app.Application
import com.example.somefood.data.room.provider.UserDataBase
import com.example.somefood.di.appModule
import com.example.somefood.di.preferenceModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin


class App: Application() {
    override fun onCreate() {

        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@App)
            modules(appModule, preferenceModule)
        }
    }
}