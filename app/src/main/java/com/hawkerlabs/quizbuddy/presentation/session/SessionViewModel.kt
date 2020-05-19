package com.hawkerlabs.quizbuddy.presentation.session

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hawkerlabs.quizbuddy.application.core.dagger.module.SCHEDULER_IO
import com.hawkerlabs.quizbuddy.application.core.dagger.module.SCHEDULER_MAIN_THREAD
import com.hawkerlabs.quizbuddy.data.api.model.question.Data
import com.hawkerlabs.quizbuddy.data.model.CurrentOption
import com.hawkerlabs.quizbuddy.data.model.Question
import com.hawkerlabs.quizbuddy.data.model.Result


import com.hawkerlabs.quizbuddy.data.model.Session
import com.hawkerlabs.quizbuddy.domain.question.GetQuestionsByCategoryUseCase
import com.hawkerlabs.quizbuddy.domain.session.SessionUseCase
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

class SessionViewModel @Inject constructor(private val sessionUseCase : SessionUseCase,
                                           private val getQuestionsByCategoryUseCase : GetQuestionsByCategoryUseCase,
                                           @Named(SCHEDULER_IO) val subscribeOnScheduler: Scheduler,
                                           @Named(SCHEDULER_MAIN_THREAD) val observeOnScheduler: Scheduler
): ViewModel(){

    private var _currentQuestion = MutableLiveData<Question>()
    private var _session = MutableLiveData<Session>()
    private var _result = MutableLiveData<Result>()

    private var selectedId = -1


    private var _finishTest = MutableLiveData<Boolean>()

    val getCurrentQuestion: LiveData<Question>
        get() = _currentQuestion


    val isTestFinished: LiveData<Boolean>
        get() = _finishTest


    val getSession: LiveData<Session>
        get() = _session


    val getResult: LiveData<Result>
        get() = _result


    @SuppressLint("CheckResult")
    private fun getNextQuestion(){
        sessionUseCase.getNextQuestion().subscribeOn(subscribeOnScheduler)
            .observeOn(observeOnScheduler)
            .subscribe(this::onQuestionResponse, this::onError)
    }

    fun onOptionSelect(selectedId :Int ){
        this.selectedId = selectedId
    }


    fun onNext(){
        getNextQuestion()
    }


    private fun onQuestionResponse(question : Data){

        var currentOptions = mutableSetOf<CurrentOption>()
        var count = 1
        var rightAnswer = 0
        question.options.map {
            var currentOption = CurrentOption(count++, it.option, false, it.isRightAnswer )
            currentOptions.add( currentOption)

            if(it.isRightAnswer){
                rightAnswer = currentOption.id
            }
        }

        var  currentQuestion = Question(question.id, question.question, currentOptions,rightAnswer )

        if(question.options.isEmpty()){
            _finishTest.value = true
            getTestResults()

        } else {
            _currentQuestion.value = currentQuestion
        }



    }


    @SuppressLint("CheckResult")
    private fun getTestResults(){
        sessionUseCase.getTestResults().subscribeOn(subscribeOnScheduler)
            .observeOn(observeOnScheduler)
            .subscribe(this::onTestResults, this::onError)
    }

    private fun onTestResults(result: Result) {


        _result.value = result


    }


    /**
     * Set the question value
     */
    private fun onResponse(questions: List<Data>) {
        sessionUseCase.initSession(questions)
        getNextQuestion()


    }


    fun onFinishTest(){

    }


    /**
     * On new test session clean the existing question live data, we do this so that user does not see stale data from a previous test session,
     * after we get the questions from the service call we set the value of the questions set again in onResponse
     */
    @SuppressLint("CheckResult")
    fun onNewSession(categoryId : String){

        _finishTest.value = false
        _currentQuestion.value = null
        getQuestionsByCategoryUseCase.invoke(categoryId).subscribeOn(subscribeOnScheduler)
            .observeOn(observeOnScheduler)
            .subscribe(this::onResponse, this::onError)

    }


    fun onPrevious(){

        _currentQuestion.value?.let { sessionUseCase.onAnswerSubmit (selectedId, it) }
    }



    fun onSubmit(){

        _currentQuestion.value?.let { sessionUseCase.onAnswerSubmit (selectedId, it) }
    }

    private fun onError(error: Throwable) {
        error // TODO handle error state here
    }

}