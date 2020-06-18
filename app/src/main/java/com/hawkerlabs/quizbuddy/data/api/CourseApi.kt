package com.hawkerlabs.quizbuddy.data.api

import com.hawkerlabs.quizbuddy.data.api.model.CoursesResponse
import com.hawkerlabs.quizbuddy.data.api.model.Endpoints
import io.reactivex.Single
import retrofit2.http.GET


/**
 *
 */
interface CourseApi{
    @GET(Endpoints.COURSES)
    fun getCourses(): Single<CoursesResponse>
}