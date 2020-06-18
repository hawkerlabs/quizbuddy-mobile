package com.hawkerlabs.quizbuddy.data.api.model


import com.fasterxml.jackson.annotation.JsonProperty
import com.hawkerlabs.quizbuddy.data.api.model.question.Option

data class Result(
    @JsonProperty("createdAt")
    val createdAt: String = "",
    @JsonProperty("_id")
    val id: String = "",
    @JsonProperty("isVisible")
    val isVisible: Boolean = false,
    @JsonProperty("options")
    val options: List<Option> = listOf(),
    @JsonProperty("question")
    val question: String = "",
    @JsonProperty("subject")
    val subject: String = "",
    @JsonProperty("updatedAt")
    val updatedAt: String = "",
    @JsonProperty("__v")
    val v: Int = 0
)