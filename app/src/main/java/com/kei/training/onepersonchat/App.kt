package com.kei.training.onepersonchat

import android.app.Application
import com.kei.training.onepersonchat.chat.di.AppComponent
import com.kei.training.onepersonchat.chat.di.AppModule
import com.kei.training.onepersonchat.chat.di.DaggerAppComponent

class App : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    fun getComponent(): AppComponent = appComponent
}