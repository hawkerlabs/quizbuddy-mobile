package com.hawkerlabs.quizbuddy.data.api

import com.hawkerlabs.quizbuddy.data.api.model.Endpoints
import com.hawkerlabs.quizbuddy.data.api.model.question.QuestionsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface QuizApi {
    @GET(Endpoints.QUESTIONS)
    fun getQuestionsBySubject(@Query("subject") subjectId: String): Single<QuestionsResponse>
}