package com.kei.training.onepersonchat

import com.kei.training.onepersonchat.chat.di.AppModule
import com.kei.training.onepersonchat.chat.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
                .appModule(AppModule("Orma_Database"))
                .create(this)
    }
}