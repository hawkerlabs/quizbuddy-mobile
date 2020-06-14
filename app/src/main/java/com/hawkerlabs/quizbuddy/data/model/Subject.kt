package com.hawkerlabs.quizbuddy.data.model


import com.fasterxml.jackson.annotation.JsonProperty

data class Subject(
    @JsonProperty("class")
    val classX: String = "",
    @JsonProperty("course")
    val course: String = "",
    @JsonProperty("createdAt")
    val createdAt: String = "",
    @JsonProperty("_id")
    val id: String = "",
    @JsonProperty("isVisible")
    val isVisible: Boolean = false,
    @JsonProperty("questions")
    val questions: List<String> = listOf(),
    @JsonProperty("title")
    val title: String = "",
    @JsonProperty("updatedAt")
    val updatedAt: String = "",
    @JsonProperty("__v")
    val v: Int = 0
)