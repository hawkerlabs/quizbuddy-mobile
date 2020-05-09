package com.hawkerlabs.quizbuddy.presentation.result.viewmodel

import androidx.lifecycle.ViewModel
import com.hawkerlabs.quizbuddy.data.model.Result


class ResultsListItemViewModel (val question : String?, private val _answer : String) : ViewModel() {


    val answer: String = _answer
        get()  = "Correct Answer:  $field"
}


