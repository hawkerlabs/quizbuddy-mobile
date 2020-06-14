package com.hawkerlabs.quizbuddy.data.api

import com.hawkerlabs.quizbuddy.data.api.model.Endpoints
import com.hawkerlabs.quizbuddy.data.api.model.SubjectsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SubjectsApi{
    @GET(Endpoints.COURSES)
    fun getSubjectsByCourse(@Query("course") courseId: String): Single<SubjectsResponse>
}