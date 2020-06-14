package com.hawkerlabs.quizbuddy.presentation.subject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hawkerlabs.quizbuddy.application.core.dagger.module.SCHEDULER_IO
import com.hawkerlabs.quizbuddy.application.core.dagger.module.SCHEDULER_MAIN_THREAD
import com.hawkerlabs.quizbuddy.data.model.Subject

import com.hawkerlabs.quizbuddy.domain.subject.GetSubjectsByCategoryUseCase
import com.hawkerlabs.quizbuddy.presentation.category.viewmodel.PageState
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Named

class SubjectsViewModel @Inject constructor(
    private val getSubjectsByCategoryUseCase: GetSubjectsByCategoryUseCase,
    @Named(SCHEDULER_IO) val subscribeOnScheduler: Scheduler,
    @Named(SCHEDULER_MAIN_THREAD) val observeOnScheduler: Scheduler
) : ViewModel() {

//    private var _subjects =
//        MutableLiveData<List<SubjectListItemViewModel>>()
//
//
//    private val subjects by lazy {
//        execute().subscribeOn(subscribeOnScheduler)
//            .observeOn(observeOnScheduler)
//            .subscribe(
//                this::onResponsePageState
//                ,this::onError
//            )
//        return@lazy _subjects
//    }
//
//
//
//    fun getSubjects(): LiveData<List<SubjectListItemViewModel>> = subjects
//
//
//    private fun execute(): Single<List<SubjectListItemViewModel>> {
//        getSubjectsByCategoryUseCase.get
//        return Single.zip(
//            getDisplayCategoriesUseCase.invoke(),
//            getFeaturedCoursesUseCase.invoke(),
//
//            BiFunction
//            { categories, courses ->
//                createPageState(categories, courses)
//            })
//    }


}