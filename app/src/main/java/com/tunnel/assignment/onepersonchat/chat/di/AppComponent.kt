package com.tunnel.assignment.onepersonchat.chat.di

import com.tunnel.assignment.onepersonchat.chat.ChatActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(
        AppModule::class))
interface AppComponent {
    fun inject(activity: ChatActivity)


}