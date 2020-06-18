package com.hawkerlabs.quizbuddy.data.api.model


import com.fasterxml.jackson.annotation.JsonProperty
import com.hawkerlabs.quizbuddy.data.model.Subject


data class SubjectsResponse(
    @JsonProperty("data")
    val subjects: List<Subject> = listOf(),
    @JsonProperty("success")
    val success: Boolean = false
)