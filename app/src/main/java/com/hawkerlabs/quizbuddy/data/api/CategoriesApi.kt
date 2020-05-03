package com.hawkerlabs.quizbuddy.data.api

import com.hawkerlabs.quizbuddy.data.api.model.CategoriesResponse
import com.hawkerlabs.quizbuddy.data.api.model.Endpoints



import io.reactivex.Single
import retrofit2.http.GET


interface CategoriesApi {

    /**
     * Get Films
     */
    @GET(Endpoints.CATEGORIES)
    fun getCategories(): Single<CategoriesResponse>



}