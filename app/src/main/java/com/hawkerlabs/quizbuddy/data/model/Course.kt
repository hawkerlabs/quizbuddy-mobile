package com.hawkerlabs.quizbuddy.data.model


import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty


/**
 * Data class for Course
 */
data class Course(
    @JsonProperty("classes")
    val classes: List<String> = listOf(),
    @JsonProperty("createdAt")
    val createdAt: String = "",
    @JsonProperty("description")
    val description: String = "",
    @JsonProperty("_id")
    val id: String = "",
    @JsonProperty("isVisible")
    val isVisible: Boolean = false,
    @JsonProperty("name")
    val name: String = "",
    @JsonProperty("updatedAt")
    val updatedAt: String = "",
    @JsonIgnore
    @JsonProperty("__v")
    val v: Int = 0
)