package com.kei.training.onepersonchat.chat.di

import com.github.gfx.android.orma.AccessThreadConstraint
import com.kei.training.onepersonchat.App
import com.kei.training.onepersonchat.chat.model.orma.OrmaDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val name: String) {

    @Provides
    @Singleton
    fun provideOrmaDatabase(app: App): OrmaDatabase {
        return OrmaDatabase.builder(app)
                .name(name)
                .readOnMainThread(AccessThreadConstraint.NONE)
                .writeOnMainThread(AccessThreadConstraint.NONE)
                .build()
    }
}