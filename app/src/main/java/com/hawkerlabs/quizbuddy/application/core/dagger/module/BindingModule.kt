package com.hawkerlabs.quizbuddy.application.core.dagger.module

import com.hawkerlabs.quizbuddy.MainActivity
import com.hawkerlabs.quizbuddy.presentation.category.ui.CategoryFragment
import com.hawkerlabs.quizbuddy.presentation.question.ui.QuestionFragment
import com.hawkerlabs.quizbuddy.presentation.result.ui.ResultsFragment
import com.hawkerlabs.quizbuddy.presentation.subject.ui.SubjectFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BindingModule {
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindCategoryFragment(): CategoryFragment

    @ContributesAndroidInjector
    abstract fun bindQuestionFragment(): QuestionFragment


    @ContributesAndroidInjector
    abstract fun bindResultsFragment(): ResultsFragment

    @ContributesAndroidInjector
    abstract fun bindSubjectFragment(): SubjectFragment




}