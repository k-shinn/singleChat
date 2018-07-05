package com.kei.training.onepersonchat.chat.di

import com.kei.training.onepersonchat.MainActivity
import com.kei.training.onepersonchat.chat.ChatActivity
import com.kei.training.onepersonchat.chat.editor.EditorFragment
import com.kei.training.onepersonchat.chat.timeline.TimelineFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class,
    ViewModelModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(timelineFragment: TimelineFragment)
    fun inject(editorFragment: EditorFragment)
}