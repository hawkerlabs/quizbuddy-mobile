package com.hawkerlabs.quizbuddy.core.dagger.component

import android.app.Application
import com.hawkerlabs.quizbuddy.core.dagger.module.AppModule
import com.hawkerlabs.quizbuddy.core.dagger.module.BindingModule
import com.hawkerlabs.quizbuddy.core.dagger.module.ViewModelFactoryModule
import com.hawkerlabs.quizbuddy.core.dagger.module.ViewModelModule
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
        BindingModule::class

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