package com.kei.training.onepersonchat

import android.app.Activity
import android.app.Application
import com.kei.training.onepersonchat.chat.di.AppModule
import com.kei.training.onepersonchat.chat.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
                .appModule(AppModule("Orma_Database"))
                .create(this)
                .inject(this)
    }
}