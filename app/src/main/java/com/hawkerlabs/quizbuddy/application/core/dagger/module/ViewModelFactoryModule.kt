package com.hawkerlabs.quizbuddy.application.core.dagger.module

import androidx.lifecycle.ViewModelProvider
import com.hawkerlabs.quizbuddy.application.core.ViewModelFactory
import dagger.Binds
import dagger.Module


@Module
abstract class ViewModelFactoryModule{

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}