package com.hawkerlabs.quizbuddy.presentation.category.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hawkerlabs.quizbuddy.application.core.dagger.module.SCHEDULER_IO
import com.hawkerlabs.quizbuddy.application.core.dagger.module.SCHEDULER_MAIN_THREAD
import com.hawkerlabs.quizbuddy.data.api.model.Category
import com.hawkerlabs.quizbuddy.domain.category.GetDisplayCategoriesUseCase
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

class CategoryViewModel @Inject constructor(
    private val getDisplayCategoriesUseCase: GetDisplayCategoriesUseCase,
    @Named(SCHEDULER_IO) val subscribeOnScheduler: Scheduler,
    @Named(SCHEDULER_MAIN_THREAD) val observeOnScheduler: Scheduler
) : ViewModel() {

    private var _categoriesListItemViewModel =
        MutableLiveData<ArrayList<CategoriesListItemViewModel>>()
    private var _categoriesList = ArrayList<CategoriesListItemViewModel>()


    private val categoriesListItemViewModelLiveData by lazy {
        getDisplayCategoriesUseCase.invoke()
            .subscribeOn(subscribeOnScheduler)
            .observeOn(observeOnScheduler)
            .subscribe(
                this::onResponse
                , this::onError
            )
        return@lazy _categoriesListItemViewModel
    }


    fun getDisplayCategories(): LiveData<ArrayList<CategoriesListItemViewModel>> =
        categoriesListItemViewModelLiveData


    /**
     * Called on pull to refresh
     */
    fun onRefresh() {
        _categoriesList.clear()
        refreshCategories()
    }

    @SuppressLint("CheckResult")
    private fun refreshCategories() {
        getDisplayCategoriesUseCase.invoke()
            .subscribeOn(subscribeOnScheduler)
            .observeOn(observeOnScheduler)
            .subscribe(this::onResponse, this::onError)
    }

    /**
     * Response handler
     */
    private fun onResponse(categories: List<Category>) {
        categories.map { item ->
            _categoriesList.add(CategoriesListItemViewModel(item))
        }
        _categoriesListItemViewModel.value = _categoriesList
    }


    private fun onError(error: Throwable) {
        error
    }


}