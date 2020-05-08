package com.hawkerlabs.quizbuddy.data.model

data class Result(
    val correctAnswers: MutableSet<Question>,
    val inCorrectAnswers: MutableSet<Question>,
    var correctAnswerCount: Int,
    var isFinished: Boolean
)

