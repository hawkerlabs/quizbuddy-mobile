package com.hawkerlabs.quizbuddy.data.model


/**
 * TODO delete
 */
data class Option(
    val id: Int,
    val text: String? = "",
    val isChecked : Boolean? = false,
    val isCorrectAnswer : Boolean? = false
)