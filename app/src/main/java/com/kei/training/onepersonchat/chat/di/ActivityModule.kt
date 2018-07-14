package com.kei.training.onepersonchat.chat.di

import com.kei.training.onepersonchat.MainActivity
import com.kei.training.onepersonchat.chat.ChatActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityModule {
    @ContributesAndroidInjector
    internal abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [ChatModule::class])
    internal abstract fun contributeChatActivity(): ChatActivity
}