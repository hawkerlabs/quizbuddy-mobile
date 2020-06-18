package com.hawkerlabs.quizbuddy.data.api.model.question


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class QuestionsResponse(
    @JsonProperty("data")
    @JsonIgnoreProperties("subject","category")
    val data: List<QuestionDTO> = listOf(),
    @JsonProperty("success")
    val success: Boolean = false
)