package com.hawkerlabs.quizbuddy.data.api.model


import com.fasterxml.jackson.annotation.JsonProperty

data class Category(
    @JsonProperty("createdAt")
    val createdAt: String = "",
    @JsonProperty("description")
    val description: String = "",
    @JsonProperty("_id")
    val id: String = "",
    @JsonProperty("image")
    val image: String = "",
    @JsonProperty("isVisible")
    val isVisible: Boolean = false,
    @JsonProperty("name")
    val name: String = "",
    @JsonProperty("updatedAt")
    val updatedAt: String = "",
    @JsonProperty("__v")
    val v: Int = 0
)