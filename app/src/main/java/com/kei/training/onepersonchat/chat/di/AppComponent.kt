package com.kei.training.onepersonchat.chat.di

import com.kei.training.onepersonchat.App
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    ActivityModule::class,
    ViewModelModule::class])
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>() {
        // モジュールにクラス引数渡したい場合追加
        // DaggerAppComponentにInjectする過程で設定する
        abstract fun appModule(appModule: AppModule): Builder
    }
}
