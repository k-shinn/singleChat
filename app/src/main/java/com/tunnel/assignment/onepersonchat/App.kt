package com.tunnel.assignment.onepersonchat

import android.app.Application

class App : Application() {
    // TODO: Dagger化
    override fun onCreate() {
        super.onCreate()
        app = this
    }

    companion object {
        lateinit var app: App
    }

}