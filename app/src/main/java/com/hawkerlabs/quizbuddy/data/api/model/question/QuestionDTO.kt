package com.hawkerlabs.quizbuddy.data.api.model.question


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty


data class QuestionDTO(
    @JsonProperty("category")
    val category: String? = "",
    @JsonProperty("subject")
    val subject: String? = "",
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
    @JsonProperty("updatedAt")
    val updatedAt: String = "",
    @JsonProperty("__v")
    val v: Int = 0
)