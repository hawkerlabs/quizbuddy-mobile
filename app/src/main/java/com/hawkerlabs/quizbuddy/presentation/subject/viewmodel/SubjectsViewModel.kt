package com.hawkerlabs.quizbuddy.presentation.subject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hawkerlabs.quizbuddy.application.core.dagger.module.SCHEDULER_IO
import com.hawkerlabs.quizbuddy.application.core.dagger.module.SCHEDULER_MAIN_THREAD
import com.hawkerlabs.quizbuddy.data.model.Subject

import com.hawkerlabs.quizbuddy.domain.subject.GetSubjectsByCategoryUseCase
import com.hawkerlabs.quizbuddy.presentation.category.viewmodel.CategoriesListItemViewModel
import com.hawkerlabs.quizbuddy.presentation.category.viewmodel.PageState
import com.hawkerlabs.quizbuddy.presentation.course.viewmodel.CoursesListItemViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Named


/**
 * Ref: https://discuss.kotlinlang.org/t/support-lazy-argument-in-functions/2127
 */




class SubjectsViewModel @Inject constructor(
    private val getSubjectsByCategoryUseCase: GetSubjectsByCategoryUseCase,
    @Named(SCHEDULER_IO) val subscribeOnScheduler: Scheduler,
    @Named(SCHEDULER_MAIN_THREAD) val observeOnScheduler: Scheduler
) : ViewModel() {
    private var _isLoading = MutableLiveData<Boolean>()
    val getLoadState: LiveData<Boolean>
        get() = _isLoading

    private lateinit var courseId :String
    private var _subjectsList = ArrayList<SubjectListItemViewModel>()
    private var _subjects =
        MutableLiveData<List<SubjectListItemViewModel>>()


    val loadSubjects: () -> MutableLiveData<List<SubjectListItemViewModel>> = {
        _subjectsList.clear()
        execute(courseId).subscribeOn(subscribeOnScheduler)
            .observeOn(observeOnScheduler)
            .subscribe(
                this::onResponse
                ,this::onError
            )
        _subjects
    }


    /**
     *
     */
    fun getSubjects(courseId : String): LiveData<List<SubjectListItemViewModel>> {

        this.courseId = courseId
        return loadSubjects()
    }




    private fun onResponse(subjectsListResponse : List<Subject>){
        _isLoading.value = false
        subjectsListResponse.map { subject ->
            _subjectsList.add(SubjectListItemViewModel(subject))
        }
        _subjects.value = _subjectsList
    }

    private fun onError(error: Throwable) {
        error
    }


    /**
     * Get subjects for the courseID
     */
    private fun execute(courseId : String): Single<List<Subject>> {
        _isLoading.value = true
       return  getSubjectsByCategoryUseCase.invoke(courseId)

    }


}