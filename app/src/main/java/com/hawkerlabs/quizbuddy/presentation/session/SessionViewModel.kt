package com.hawkerlabs.quizbuddy.presentation.session

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hawkerlabs.quizbuddy.application.core.dagger.module.SCHEDULER_IO
import com.hawkerlabs.quizbuddy.application.core.dagger.module.SCHEDULER_MAIN_THREAD

import com.hawkerlabs.quizbuddy.data.model.Question
import com.hawkerlabs.quizbuddy.data.model.Session
import com.hawkerlabs.quizbuddy.domain.session.SessionUseCase
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

class SessionViewModel @Inject constructor(private val sessionUseCase : SessionUseCase,
                                           @Named(SCHEDULER_IO) val subscribeOnScheduler: Scheduler,
                                           @Named(SCHEDULER_MAIN_THREAD) val observeOnScheduler: Scheduler
): ViewModel(){

    private var _question = MutableLiveData<Question>()
    private var _session = MutableLiveData<Session>()

    private var selectedId = -1


    private var _finishTest = MutableLiveData<Boolean>()
    val getQuestion: LiveData<Question>
        get() = _question


    val getTestState: LiveData<Boolean>
        get() = _finishTest


    val getSession: LiveData<Session>
        get() = _session

    init{
        getNextQuestion()
    }

    @SuppressLint("CheckResult")
    private fun initialize(){

        sessionUseCase.getNextQuestion().subscribeOn(subscribeOnScheduler)
            .observeOn(observeOnScheduler)
            .subscribe(this::onTestStart, this::onError)

    }

    private fun onTestStart(question: Question){

        //Create a new session
        _session.value = Session(true, question, 0, false )

    }

    @SuppressLint("CheckResult")
    private fun getNextQuestion(){
        sessionUseCase.getNextQuestion().subscribeOn(subscribeOnScheduler)
            .observeOn(observeOnScheduler)
            .subscribe(this::onResponse, this::onError)
    }

    fun onOptionSelect(selectedId :Int ){
        this.selectedId = selectedId
    }


    fun onNext(){
        getNextQuestion()
    }


    private fun onResponse(question: Question) {
        if(question.questionText.isEmpty()){
            _finishTest.value = true

            _session.value = Session(false, question, 0, true )
        } else {
            _session.value = Session(true, question, 0, false )

//            _question.value = question
        }

    }

    fun onSubmit(){
        sessionUseCase.onAnswerSubmit (selectedId)
    }

    private fun onError(error: Throwable) {
        error // TODO handle error state here
    }

}