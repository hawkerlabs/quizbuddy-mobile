package com.hawkerlabs.quizbuddy.data.api.model.question


import com.fasterxml.jackson.annotation.JsonProperty

data class Option(
    @JsonProperty("_id")
    val id: String = "",
    @JsonProperty("isRightAnswer")
    val isRightAnswer: Boolean = false,
    @JsonProperty("option")
    val option: String = ""
)