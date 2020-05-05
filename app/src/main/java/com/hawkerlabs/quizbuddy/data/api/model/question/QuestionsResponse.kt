package com.hawkerlabs.quizbuddy.data.api.model.question


import com.fasterxml.jackson.annotation.JsonProperty

data class QuestionsResponse(
    @JsonProperty("data")
    val data: List<Question> = listOf(),
    @JsonProperty("success")
    val success: Boolean = false
)