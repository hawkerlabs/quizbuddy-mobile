package com.hawkerlabs.quizbuddy.presentation.question.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hawkerlabs.quizbuddy.application.core.dagger.module.SCHEDULER_IO
import com.hawkerlabs.quizbuddy.application.core.dagger.module.SCHEDULER_MAIN_THREAD
import com.hawkerlabs.quizbuddy.data.model.Question
import com.hawkerlabs.quizbuddy.domain.session.SessionUseCase
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

class QuestionViewModel @Inject constructor(private val sessionUseCase : SessionUseCase, @Named(SCHEDULER_IO) val subscribeOnScheduler: Scheduler,
                                            @Named(SCHEDULER_MAIN_THREAD) val observeOnScheduler: Scheduler
): ViewModel(){


    private var _question = MutableLiveData<Question>()
    private var _finishTest = MutableLiveData<Boolean>()

    val isFinishTest: LiveData<Boolean>
        get() = _finishTest

    val getQuestion: LiveData<Question>
        get() = _question


    init{
//       var options  = mutableSetOf<Option>()
//        options.add(Option(21, "1st question", false))
//        options.add(Option(2, "2nd question", false))
//        _question.value =  Question("someId", "WHat is something?", options)


        getNextQuestion()
    }


    @SuppressLint("CheckResult")
    private fun getNextQuestion(){
        sessionUseCase.getNextQuestion().subscribeOn(subscribeOnScheduler)
            .observeOn(observeOnScheduler)
            .subscribe(this::onResponse, this::onError)
    }


    fun onNext(){
        getNextQuestion()
    }
    private fun onResponse(question: Question) {
        if(question.questionText.isEmpty()){
            _finishTest.value = true
        } else {
            _question.value = question
        }

    }

    private fun onError(error: Throwable) {
        error // TODO handle error state here
    }
}