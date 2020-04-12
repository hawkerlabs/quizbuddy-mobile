package com.hawkerlabs.quizbuddy.core.dagger.module

import com.hawkerlabs.quizbuddy.MainActivity
import com.hawkerlabs.quizbuddy.presentation.category.ui.CategoryFragment
import com.hawkerlabs.quizbuddy.presentation.question.ui.QuestionFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.DaggerFragment

@Module
abstract class BindingModule {
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindCategoryFragment(): CategoryFragment

    @ContributesAndroidInjector
    abstract fun bindQuestionFragment(): QuestionFragment

}