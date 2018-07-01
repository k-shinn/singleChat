package com.tunnel.assignment.onepersonchat

import android.app.Application
import com.tunnel.assignment.onepersonchat.chat.di.AppComponent
import com.tunnel.assignment.onepersonchat.chat.di.AppModule
import com.tunnel.assignment.onepersonchat.chat.di.DaggerAppComponent

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