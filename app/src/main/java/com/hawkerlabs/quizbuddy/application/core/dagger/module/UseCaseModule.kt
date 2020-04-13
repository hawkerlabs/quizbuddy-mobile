package com.hawkerlabs.quizbuddy.application.core.dagger.module

import com.hawkerlabs.quizbuddy.domain.question.GetQuestionsUseCase
import com.hawkerlabs.quizbuddy.domain.question.impl.GetQuestionsUseCaseImpl
import com.hawkerlabs.quizbuddy.domain.session.SessionUseCase
import com.hawkerlabs.quizbuddy.domain.session.impl.SessionUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
abstract class UseCaseModule {

    @Binds
    abstract fun provideGetQuestionsUseCase(getQuestionsUseCase: GetQuestionsUseCaseImpl): GetQuestionsUseCase

    @Binds
    abstract fun provideSessionUseCase(sessionUseCase: SessionUseCaseImpl): SessionUseCase
}