package com.hawkerlabs.quizbuddy.application.core.dagger.module

import com.hawkerlabs.quizbuddy.data.repository.CategoriesRepository
import com.hawkerlabs.quizbuddy.data.repository.CoursesRepository
import com.hawkerlabs.quizbuddy.data.repository.QuestionsRepository
import com.hawkerlabs.quizbuddy.data.repository.impl.CategoriesRepositoryImpl
import com.hawkerlabs.quizbuddy.data.repository.impl.CoursesRepositoryImpl
import com.hawkerlabs.quizbuddy.data.repository.impl.QuestionsRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideCategoriesRepository(categoriesRepositoryImpl: CategoriesRepositoryImpl): CategoriesRepository

    @Binds
    abstract fun provideQuestionsRepository(questionsRepository: QuestionsRepositoryImpl): QuestionsRepository

    @Binds
    abstract fun provideCoursesRepository(coursesRepository: CoursesRepositoryImpl): CoursesRepository


}