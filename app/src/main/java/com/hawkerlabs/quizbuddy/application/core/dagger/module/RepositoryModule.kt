package com.hawkerlabs.quizbuddy.application.core.dagger.module

import com.hawkerlabs.quizbuddy.data.repository.CategoriesRepository
import com.hawkerlabs.quizbuddy.data.repository.QuestionsRepository
import com.hawkerlabs.quizbuddy.data.repository.impl.CategoriesRepositoryImpl
import com.hawkerlabs.quizbuddy.data.repository.impl.QuestionsRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideCategoriesRepository(categoriesRepositoryImpl: CategoriesRepositoryImpl): CategoriesRepository

    @Binds
    abstract fun provideQuestionsRepository(QuestionsRepositoryImpl: QuestionsRepositoryImpl): QuestionsRepository



}