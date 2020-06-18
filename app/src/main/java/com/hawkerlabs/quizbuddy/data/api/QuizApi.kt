package com.hawkerlabs.quizbuddy.data.api

import com.hawkerlabs.quizbuddy.data.api.model.Endpoints
import com.hawkerlabs.quizbuddy.data.api.model.QuizSessionResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface QuizApi {
    @GET(Endpoints.QUIZ)
    fun getQuestionsBySubject(@Query("subject") subjectId: String): Single<QuizSessionResponse>
}