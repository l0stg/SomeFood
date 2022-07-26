package com.example.somefood.app

import android.app.Application
import com.example.somefood.data.room.provider.UserDataBase
import com.example.somefood.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin


class App: Application() {
    override fun onCreate() {

        UserDataBase.create(this)

        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@App)
            modules(appModule)
        }
    }
}