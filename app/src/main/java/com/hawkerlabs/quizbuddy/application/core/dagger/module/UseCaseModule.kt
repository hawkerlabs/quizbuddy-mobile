package com.hawkerlabs.quizbuddy.application.core.dagger.module

import com.hawkerlabs.quizbuddy.domain.category.GetDisplayCategoriesUseCase
import com.hawkerlabs.quizbuddy.domain.category.impl.GetDisplayCategoriesUseCaseImpl
import com.hawkerlabs.quizbuddy.domain.course.GetFeaturedCoursesUseCase
import com.hawkerlabs.quizbuddy.domain.course.impl.GetFeaturedCoursesUseCaseImpl
import com.hawkerlabs.quizbuddy.domain.quiz.QuizUseCase
import com.hawkerlabs.quizbuddy.domain.quiz.impl.QuizUseCaseImpl
import com.hawkerlabs.quizbuddy.domain.session.GetScoreUseCase
import com.hawkerlabs.quizbuddy.domain.session.SessionUseCase
import com.hawkerlabs.quizbuddy.domain.session.impl.GetScoreUseCaseImpl
import com.hawkerlabs.quizbuddy.domain.session.impl.SessionUseCaseImpl
import com.hawkerlabs.quizbuddy.domain.subject.GetSubjectsByCategoryUseCase
import com.hawkerlabs.quizbuddy.domain.subject.impl.GetSubjectsByCategoryUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
abstract class UseCaseModule {


    @Binds
    abstract fun provideSessionUseCase(sessionUseCase: SessionUseCaseImpl): SessionUseCase

    @Binds
    abstract fun provideGetDisplayCategoriesUseCase(getDisplayCategoriesUseCase: GetDisplayCategoriesUseCaseImpl): GetDisplayCategoriesUseCase

//    @Binds
//    abstract fun provideGetQuestionsByCategoryUseCase(sessionUseCase: GetQuestionsByCategoryUseCaseImpl): GetQuestionsByCategoryUseCase

    @Binds
    abstract fun provideGetScoreUseCase(getScoreUseCase: GetScoreUseCaseImpl): GetScoreUseCase

    @Binds
    abstract fun provideGetFeaturedCoursesUseCase(getFeaturedCoursesUseCase: GetFeaturedCoursesUseCaseImpl): GetFeaturedCoursesUseCase

    @Binds
    abstract fun provideGetSubjectsByCategoryUseCase(getSubjectsByCategoryUseCase: GetSubjectsByCategoryUseCaseImpl): GetSubjectsByCategoryUseCase


    @Binds
    abstract fun provideQuizUseCase(getSubjectsByCategoryUseCase: QuizUseCaseImpl): QuizUseCase


}