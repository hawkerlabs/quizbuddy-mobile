package com.hawkerlabs.quizbuddy.data.repository

import com.hawkerlabs.quizbuddy.data.api.model.Category
import io.reactivex.Single


interface CategoriesRepository {

    fun getCategories() : Single<List<Category>>
}