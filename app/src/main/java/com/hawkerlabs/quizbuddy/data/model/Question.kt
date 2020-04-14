package com.hawkerlabs.quizbuddy.data.model


/**
 * Question dto
 */
data class Question(
    val id: String,
    val questionText : String,
    val options: Set<Option>,
    val correctAnswer : Int
)