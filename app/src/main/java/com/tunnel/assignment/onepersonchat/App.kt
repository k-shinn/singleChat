package com.tunnel.assignment.onepersonchat

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        app = this
    }

    companion object {
        public lateinit var app: App
    }

}