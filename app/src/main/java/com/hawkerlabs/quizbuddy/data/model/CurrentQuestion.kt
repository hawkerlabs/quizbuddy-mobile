package com.hawkerlabs.quizbuddy.data.model

import com.hawkerlabs.quizbuddy.data.api.model.question.QuestionDTO

data class CurrentQuestion(
    val question: QuestionDTO,
    val index: String,
    val questionIndex: Int

)