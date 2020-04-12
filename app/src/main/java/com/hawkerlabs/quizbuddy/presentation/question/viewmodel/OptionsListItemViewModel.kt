package com.hawkerlabs.quizbuddy.presentation.question.viewmodel

import androidx.lifecycle.ViewModel
import com.hawkerlabs.quizbuddy.data.model.Option


/**
 *
 */
class OptionsListItemViewModel(option : Option) : ViewModel(){

    val id = option.id
    val optionText = option.text
    val isCorrectAnswer = option.isCorrectAnswer

}