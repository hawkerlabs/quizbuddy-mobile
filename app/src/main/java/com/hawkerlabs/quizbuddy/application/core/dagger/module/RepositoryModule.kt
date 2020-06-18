package com.hawkerlabs.quizbuddy.application.core.dagger.module

import com.hawkerlabs.quizbuddy.data.repository.*
import com.hawkerlabs.quizbuddy.data.repository.impl.*
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

    @Binds
    abstract fun provideSubjectsRepository(subjectsRepository: SubjectsRepositoryImpl): SubjectsRepository

    @Binds
    abstract fun provideQuizRepository(quizRepository: QuizRepositoryImpl): QuizRepository

}