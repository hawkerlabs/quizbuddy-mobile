package com.hawkerlabs.quizbuddy.presentation.result.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hawkerlabs.quizbuddy.application.core.dagger.module.SCHEDULER_IO
import com.hawkerlabs.quizbuddy.application.core.dagger.module.SCHEDULER_MAIN_THREAD
import com.hawkerlabs.quizbuddy.data.model.Result
import com.hawkerlabs.quizbuddy.data.model.Score
import com.hawkerlabs.quizbuddy.domain.session.GetScoreUseCase
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

class ResultsViewModel  @Inject constructor(private val getScoreUseCase : GetScoreUseCase, @Named(
    SCHEDULER_IO) val subscribeOnScheduler: Scheduler, @Named(SCHEDULER_MAIN_THREAD) val observeOnScheduler: Scheduler): ViewModel(){


    private var _score = MutableLiveData<Score>()

    val getScore: LiveData<Score>
        get() = _score



    /**
     *
     */
    @SuppressLint("CheckResult")
    public fun onResults(result: Result) {

        getScoreUseCase.invoke(result).subscribeOn(subscribeOnScheduler)
            .observeOn(observeOnScheduler)
            .subscribe(this::onResponse, this::onError)






    }


    /**
     *
     */
    private fun onResponse(score: Score) {
        _score.value = score
    }
    private fun onError(error: Throwable) {
        error // TODO handle error state here
    }

}