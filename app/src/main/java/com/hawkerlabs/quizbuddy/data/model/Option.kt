package com.hawkerlabs.quizbuddy.data.model

data class Option(
    val id: String? = "",
    val text: String? = "",
    val isCorrectAnswer : Boolean? = false
)