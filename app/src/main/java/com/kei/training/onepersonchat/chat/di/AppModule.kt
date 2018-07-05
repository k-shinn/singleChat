package com.kei.training.onepersonchat.chat.di

import android.app.Application
import android.content.Context
import com.github.gfx.android.orma.AccessThreadConstraint
import com.kei.training.onepersonchat.chat.model.orma.OrmaDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Provides
    fun provideContext(): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideOrmaDatabase(): OrmaDatabase {
        return OrmaDatabase.builder(application)
                .name("Orma_Database")
                .readOnMainThread(AccessThreadConstraint.NONE)
                .writeOnMainThread(AccessThreadConstraint.NONE)
                .build()
    }
}