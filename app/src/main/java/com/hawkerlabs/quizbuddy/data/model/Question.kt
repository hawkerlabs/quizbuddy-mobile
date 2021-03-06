package com.hawkerlabs.quizbuddy.data.model



/**
 * Question dto
 */
data class Question(
    val id: String,
    val questionText : String,
    val options: Set<CurrentOption>,
    val correctAnswer : Int
)