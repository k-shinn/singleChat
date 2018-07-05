package com.kei.training.onepersonchat.chat.di

import com.kei.training.onepersonchat.chat.editor.EditorFragment
import com.kei.training.onepersonchat.chat.timeline.TimelineFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ChatModule {
    @ContributesAndroidInjector
    abstract fun contributeTimelineFragment(): TimelineFragment

    @ContributesAndroidInjector
    abstract fun contributeEditorFragment(): EditorFragment
}