package com.hawkerlabs.quizbuddy.data.model

data class Session(
    var isActive : Boolean,

    var correctAnswerCount :Int,
    var isFinished :Boolean
)