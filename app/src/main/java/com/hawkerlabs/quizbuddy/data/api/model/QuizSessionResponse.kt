package com.hawkerlabs.quizbuddy.data.api.model


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
@JsonIgnoreProperties(ignoreUnknown = true)


data class QuizSessionResponse(
    @JsonProperty("data")
    val `data`: Data = Data(),
    @JsonProperty("success")
    val success: Boolean = false
)