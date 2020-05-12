package com.hawkerlabs.quizbuddy.data.repository

import com.hawkerlabs.quizbuddy.data.api.model.question.Data
import io.reactivex.Single

interface QuestionsRepository {

    fun getQuestionsByCategory(categoryId : String) : Single<List<Data>>
}