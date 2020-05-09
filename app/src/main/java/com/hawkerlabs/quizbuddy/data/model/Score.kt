package com.hawkerlabs.quizbuddy.data.model


class Score(
    val message: String,
    private val _scorePercentage: String

) {

    val scorePercentage: String = _scorePercentage
        get()  = "$field%"
}

