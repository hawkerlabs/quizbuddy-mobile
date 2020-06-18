package com.hawkerlabs.quizbuddy.data.api.model


import com.fasterxml.jackson.annotation.JsonProperty
import com.hawkerlabs.quizbuddy.data.model.Course

data class CoursesResponse(
    @JsonProperty("data")
    val `data`: List<Course> = listOf(),
    @JsonProperty("success")
    val success: Boolean = false
)