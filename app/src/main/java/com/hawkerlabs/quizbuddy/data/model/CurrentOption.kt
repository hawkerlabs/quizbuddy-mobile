package com.hawkerlabs.quizbuddy.data.model

/**
 *
 */
data class CurrentOption(
    val id: Int,
    val text: String? = "",
    val isChecked : Boolean? = false,
    val isCorrectAnswer : Boolean? = false
)