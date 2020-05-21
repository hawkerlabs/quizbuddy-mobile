package com.hawkerlabs.quizbuddy.data.model

import com.hawkerlabs.quizbuddy.data.api.model.question.Data

data class CurrentQuestion(
    val question: Data,
    val index: String,
    val questionIndex: Int

)