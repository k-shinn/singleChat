package com.tunnel.assignment.onepersonchat.chat.di

import com.tunnel.assignment.onepersonchat.chat.ChatActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityModule {

    @ContributesAndroidInjector(modules = arrayOf(ChatActivity::class))
    internal abstract fun contributeChatActivity(): ChatActivity
}