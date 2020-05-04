package com.hawkerlabs.quizbuddy.application.core.dagger.module

import androidx.lifecycle.ViewModel
import com.hawkerlabs.quizbuddy.application.core.ViewModelKey
import com.hawkerlabs.quizbuddy.presentation.category.viewmodel.CategoryViewModel
import com.hawkerlabs.quizbuddy.presentation.question.viewmodel.QuestionViewModel
import com.hawkerlabs.quizbuddy.presentation.result.viewmodel.ResultsViewModel
import com.hawkerlabs.quizbuddy.presentation.session.SessionViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(QuestionViewModel::class)
    internal abstract  fun bindQuestionViewModel(viewModel: QuestionViewModel) : ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(SessionViewModel::class)
    internal abstract  fun bindSessionViewModel(viewModel: SessionViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ResultsViewModel::class)
    internal abstract  fun bindResultsViewModel(viewModel: ResultsViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CategoryViewModel::class)
    internal abstract  fun bindCategoryViewModel(viewModel: CategoryViewModel) : ViewModel



}