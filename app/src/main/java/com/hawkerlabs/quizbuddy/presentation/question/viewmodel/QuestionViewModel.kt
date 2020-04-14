package com.hawkerlabs.quizbuddy.presentation.question.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hawkerlabs.quizbuddy.application.core.dagger.module.SCHEDULER_IO
import com.hawkerlabs.quizbuddy.application.core.dagger.module.SCHEDULER_MAIN_THREAD
import com.hawkerlabs.quizbuddy.data.model.Option
import com.hawkerlabs.quizbuddy.data.model.Question
import com.hawkerlabs.quizbuddy.domain.session.SessionUseCase
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named


/**
 * https://medium.com/androiddevelopers/livedata-with-snackbar-navigation-and-other-events-the-singleliveevent-case-ac2622673150
 */
class QuestionViewModel @Inject constructor(private val sessionUseCase : SessionUseCase, @Named(SCHEDULER_IO) val subscribeOnScheduler: Scheduler,
                                            @Named(SCHEDULER_MAIN_THREAD) val observeOnScheduler: Scheduler
): ViewModel(){


    private var _question = MutableLiveData<Question>()
    private var _finishTest = MutableLiveData<Boolean>()

    private var _options =  MutableLiveData<Map<Int,Option>>()

    private var selectedId = -1

    val isFinishTest: LiveData<Boolean>
        get() = _finishTest

    val getQuestion: LiveData<Question>
        get() = _question


    val getOptions : LiveData<Map<Int,Option>>
        get() = _options


    init{



        getNextQuestion()
    }


    @SuppressLint("CheckResult")
    private fun getNextQuestion(){
        sessionUseCase.getNextQuestion().subscribeOn(subscribeOnScheduler)
            .observeOn(observeOnScheduler)
            .subscribe(this::onResponse, this::onError)
    }


    /**
     *
     */
    fun onOptionSelect(selectedId :Int ){
       this.selectedId = selectedId
    }

    fun onNext(){
        getNextQuestion()
    }
    private fun onResponse(question: Question) {
        if(question.questionText.isEmpty()){
            _finishTest.value = true
        } else {


            val optionsMap = mutableMapOf<Int, Option>()

            question.options.map {option ->

                optionsMap.put(option.id, option)
            }
            _options.value = optionsMap
            _question.value = question
        }

    }


    /**
     *
     */
    fun onSubmit(){


        sessionUseCase.onAnswerSubmit (selectedId)
    }

    private fun onError(error: Throwable) {
        error // TODO handle error state here
    }
}