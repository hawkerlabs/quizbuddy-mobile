package com.hawkerlabs.quizbuddy.data.api.model


import com.fasterxml.jackson.annotation.JsonProperty

data class CategoriesResponse(
    @JsonProperty("data")
    val data: List<Category> = listOf(),
    @JsonProperty("success")
    val success: Boolean = false
)