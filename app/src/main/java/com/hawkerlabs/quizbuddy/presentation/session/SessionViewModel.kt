package com.hawkerlabs.quizbuddy.presentation.session

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hawkerlabs.quizbuddy.application.core.dagger.module.SCHEDULER_IO
import com.hawkerlabs.quizbuddy.application.core.dagger.module.SCHEDULER_MAIN_THREAD

import com.hawkerlabs.quizbuddy.data.model.Option
import com.hawkerlabs.quizbuddy.data.model.Question
import com.hawkerlabs.quizbuddy.domain.question.GetQuestionsUseCase
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

class SessionViewModel @Inject constructor(private val getQuestionsUseCase: GetQuestionsUseCase, @Named(SCHEDULER_IO) val subscribeOnScheduler: Scheduler,
                                           @Named(SCHEDULER_MAIN_THREAD) val observeOnScheduler: Scheduler
): ViewModel(){



    private var _questions = MutableLiveData<List<Question>>()

    val getQuestions: LiveData<List<Question>>
        get() = _questions

   init {


       initialize()

    }


    @SuppressLint("CheckResult")
    private fun initialize(){
        getQuestionsUseCase.invoke()
            .subscribeOn(subscribeOnScheduler)
            .observeOn(observeOnScheduler)
            .subscribe(this::onResponse, this::onError)
    }


    private fun onResponse(questions: List<Question>) {
        _questions.value = questions
    }

    private fun onError(error: Throwable) {
        error // TODO handle error state here
    }

}