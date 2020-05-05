package com.hawkerlabs.quizbuddy.data.api

import com.hawkerlabs.quizbuddy.data.api.model.CategoriesResponse
import com.hawkerlabs.quizbuddy.data.api.model.Endpoints
import com.hawkerlabs.quizbuddy.data.api.model.question.QuestionsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface QuestionsApi {
    /**
     * Get Films
     */
    @GET(Endpoints.QUESTIONS)
    fun getQuestionsByCategory(@Query("category") categoryId: String): Single<QuestionsResponse>
}