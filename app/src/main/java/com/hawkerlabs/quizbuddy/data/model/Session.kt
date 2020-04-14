package com.hawkerlabs.quizbuddy.data.model

data class Session(
    var isActive : Boolean,
    var currentQuestion : Question,

    var correctAnswerCount :Int,
    var isFinished :Boolean
)