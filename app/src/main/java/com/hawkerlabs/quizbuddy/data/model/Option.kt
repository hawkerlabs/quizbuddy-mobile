package com.hawkerlabs.quizbuddy.data.model

data class Option(
    val id: Int,
    val text: String? = "",
    val isCorrectAnswer : Boolean? = false
)