package com.hawkerlabs.quizbuddy.data.repository.impl

import com.hawkerlabs.quizbuddy.data.api.CategoriesApi
import com.hawkerlabs.quizbuddy.data.api.QuestionsApi
import com.hawkerlabs.quizbuddy.data.api.model.question.QuestionDTO
import com.hawkerlabs.quizbuddy.data.repository.QuestionsRepository
import io.reactivex.Single
import javax.inject.Inject

class QuestionsRepositoryImpl @Inject constructor(private val questionsApi: QuestionsApi): QuestionsRepository {


    /**
     *
     */
    override fun getQuestionsByCategory(categoryId: String): Single<List<QuestionDTO>> {
        return questionsApi.getQuestionsByCategory(categoryId).map {
            it.data
        }
    }

}