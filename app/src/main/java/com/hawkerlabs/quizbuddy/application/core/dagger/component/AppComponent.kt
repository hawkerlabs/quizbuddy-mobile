package com.hawkerlabs.quizbuddy.application.core.dagger.component

import android.app.Application
import com.hawkerlabs.quizbuddy.application.core.dagger.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ViewModelFactoryModule::class,
        AppModule::class,
        ViewModelModule::class,
        BindingModule::class,
        UseCaseModule::class

    ]
)
interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}