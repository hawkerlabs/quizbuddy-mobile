package com.hawkerlabs.quizbuddy.data.repository.impl

import com.hawkerlabs.quizbuddy.data.api.CategoriesApi
import com.hawkerlabs.quizbuddy.data.api.model.Category
import com.hawkerlabs.quizbuddy.data.repository.CategoriesRepository
import io.reactivex.Single
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(private val categoriesApi: CategoriesApi): CategoriesRepository {
    override fun getCategories(): Single<List<Category>> {
        TODO("Not yet implemented")
    }

}