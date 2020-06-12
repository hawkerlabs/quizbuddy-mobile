package com.hawkerlabs.quizbuddy.presentation.category.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hawkerlabs.quizbuddy.application.core.dagger.module.SCHEDULER_IO
import com.hawkerlabs.quizbuddy.application.core.dagger.module.SCHEDULER_MAIN_THREAD
import com.hawkerlabs.quizbuddy.data.api.model.Category
import com.hawkerlabs.quizbuddy.data.model.Course
import com.hawkerlabs.quizbuddy.domain.category.GetDisplayCategoriesUseCase
import com.hawkerlabs.quizbuddy.domain.course.GetFeaturedCoursesUseCase
import com.hawkerlabs.quizbuddy.presentation.course.viewmodel.CoursesListItemViewModel
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.functions.BiFunction

import javax.inject.Inject
import javax.inject.Named


/**
 * Model to handle page state
 */
data class PageState(
    val categories: List<CategoriesListItemViewModel>,
    val courses: List<CoursesListItemViewModel>,
    val isSuccess: Boolean
)


class CategoryViewModel @Inject constructor(
    private val getDisplayCategoriesUseCase: GetDisplayCategoriesUseCase,
    private val getFeaturedCoursesUseCase: GetFeaturedCoursesUseCase,
    @Named(SCHEDULER_IO) val subscribeOnScheduler: Scheduler,
    @Named(SCHEDULER_MAIN_THREAD) val observeOnScheduler: Scheduler
) : ViewModel() {


    private var _categoriesList = ArrayList<CategoriesListItemViewModel>()
    private var _coursesList = ArrayList<CoursesListItemViewModel>()


    private var _pageState =
        MutableLiveData<PageState>()

    private val pageState by lazy {
        execute().subscribeOn(subscribeOnScheduler)
            .observeOn(observeOnScheduler)
            .subscribe(
                this::onResponsePageState
                , this::onError
            )
        return@lazy _pageState
    }


    private fun execute(): Single<PageState> {

        return Single.zip(
            getDisplayCategoriesUseCase.invoke(),
            getFeaturedCoursesUseCase.invoke(),

            BiFunction
            { categories, courses ->
                createPageState(categories, courses)
            })
    }


    private fun createPageState(
        categories: List<Category>,
        courses: List<Course>

    ): PageState {
        categories.map { category ->
            _categoriesList.add(CategoriesListItemViewModel(category))
        }

        courses.map { course ->
            _coursesList.add(CoursesListItemViewModel(course))
        }

        return PageState(_categoriesList, _coursesList, true)
    }

    fun getPageState(): LiveData<PageState> =
        pageState


    /**
     * Called on pull to refresh
     */
    fun onRefresh() {
        _categoriesList.clear()
        _coursesList.clear()

        refresh()
    }

    @SuppressLint("CheckResult")
    private fun refresh() {
        execute().subscribeOn(subscribeOnScheduler)
            .observeOn(observeOnScheduler)
            .subscribe(
                this::onResponsePageState
                , this::onError
            )
    }

    private fun onResponsePageState(state: PageState) {
        _pageState.value = state
    }


    private fun onError(error: Throwable) {
        PageState(_categoriesList, _coursesList, false)
    }


}