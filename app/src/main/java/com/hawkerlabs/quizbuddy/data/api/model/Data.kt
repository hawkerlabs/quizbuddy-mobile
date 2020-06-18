package com.hawkerlabs.quizbuddy.data.api.model


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.hawkerlabs.quizbuddy.data.api.model.question.QuestionDTO
@JsonInclude(JsonInclude.Include.NON_EMPTY)

data class Data(
    @JsonProperty("results")
    @JsonIgnoreProperties("subject","category")

    val results: List<QuestionDTO> = listOf(),
    @JsonProperty("success")
    val success: Boolean = false
)