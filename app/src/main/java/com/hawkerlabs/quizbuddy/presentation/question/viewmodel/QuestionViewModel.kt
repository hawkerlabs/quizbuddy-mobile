package com.hawkerlabs.quizbuddy.presentation.question.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hawkerlabs.quizbuddy.data.model.Option
import com.hawkerlabs.quizbuddy.data.model.Question
import javax.inject.Inject

class QuestionViewModel @Inject constructor(): ViewModel(){


    private var _question = MutableLiveData<Question>()

    val getQuestion: LiveData<Question>
        get() = _question


    init{
        var options  = mutableSetOf<Option>()
        options.add(Option("", "1st question", false))
        options.add(Option("", "2nd question", false))
        _question.value =  Question("someId", "WHat is something?", options)
    }
}