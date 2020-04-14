package com.hawkerlabs.quizbuddy.data.model

data class SessionState(
    var isActive : Boolean,
    var currentQuestion : Question
)