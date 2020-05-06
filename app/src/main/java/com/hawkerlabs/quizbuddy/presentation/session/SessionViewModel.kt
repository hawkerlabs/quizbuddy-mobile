package com.hawkerlabs.quizbuddy.presentation.session

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hawkerlabs.quizbuddy.application.core.dagger.module.SCHEDULER_IO
import com.hawkerlabs.quizbuddy.application.core.dagger.module.SCHEDULER_MAIN_THREAD
import com.hawkerlabs.quizbuddy.data.api.model.Category
import com.hawkerlabs.quizbuddy.data.api.model.question.Question

import com.hawkerlabs.quizbuddy.data.model.Session
import com.hawkerlabs.quizbuddy.domain.question.GetQuestionsByCategoryUseCase
import com.hawkerlabs.quizbuddy.domain.session.SessionUseCase
import com.hawkerlabs.quizbuddy.presentation.category.viewmodel.CategoriesListItemViewModel
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

class SessionViewModel @Inject constructor(private val sessionUseCase : SessionUseCase,
                                           private val getQuestionsByCategoryUseCase : GetQuestionsByCategoryUseCase,
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


    private fun onQuestionResponse(question : Question){

//        val currentQuestion =  com.hawkerlabs.quizbuddy.data.model.Question(question.id, question.question, question.question)
//        _session.value = Session(true, Question(), 0, false )

    }

    private fun onResponse(questions: List<Question>) {


        sessionUseCase.initSession(questions)
        getNextQuestion()
//        if(question.questionText.isEmpty()){
//            _finishTest.value = true
//
//            _session.value = Session(false, question, 0, true )
//        } else {
//            _session.value = Session(true, question, 0, false )
//
//        }

    }


    fun onFinishTest(){
//        sessionUseCase.initSession()
    }

    @SuppressLint("CheckResult")
    fun onNewSession(categoryId : String){
        _finishTest.value = false
        getQuestionsByCategoryUseCase.invoke(categoryId).subscribeOn(subscribeOnScheduler)
            .observeOn(observeOnScheduler)
            .subscribe(this::onResponse, this::onError)

    }




    fun onSubmit(){
        sessionUseCase.onAnswerSubmit (selectedId)
    }

    private fun onError(error: Throwable) {
        error // TODO handle error state here
    }

}