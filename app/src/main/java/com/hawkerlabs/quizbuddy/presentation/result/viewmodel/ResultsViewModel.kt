package com.hawkerlabs.quizbuddy.presentation.result.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hawkerlabs.quizbuddy.application.core.dagger.module.SCHEDULER_IO
import com.hawkerlabs.quizbuddy.application.core.dagger.module.SCHEDULER_MAIN_THREAD
import com.hawkerlabs.quizbuddy.data.api.model.question.Data
import com.hawkerlabs.quizbuddy.data.model.Result
import com.hawkerlabs.quizbuddy.domain.session.SessionUseCase
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

class ResultsViewModel  @Inject constructor(private val sessionUseCase : SessionUseCase, @Named(
    SCHEDULER_IO) val subscribeOnScheduler: Scheduler,@Named(SCHEDULER_MAIN_THREAD) val observeOnScheduler: Scheduler): ViewModel(){


    private var _result = MutableLiveData<Result>()

    val getResult: LiveData<Result>
        get() = _result

    init{
        getTestResults()
    }

    @SuppressLint("CheckResult")
    private fun getTestResults(){
        sessionUseCase.getTestResults().subscribeOn(subscribeOnScheduler)
            .observeOn(observeOnScheduler)
            .subscribe(this::onResults, this::onError)
    }


    /**
     *
     */
    private fun onResults(result: Result) {


        _result.value = result


    }
    private fun onError(error: Throwable) {
        error // TODO handle error state here
    }

}