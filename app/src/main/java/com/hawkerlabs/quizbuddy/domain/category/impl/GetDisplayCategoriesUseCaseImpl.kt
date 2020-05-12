package com.hawkerlabs.quizbuddy.domain.category.impl

import com.hawkerlabs.quizbuddy.data.api.model.Category
import com.hawkerlabs.quizbuddy.data.repository.CategoriesRepository
import com.hawkerlabs.quizbuddy.domain.category.GetDisplayCategoriesUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetDisplayCategoriesUseCaseImpl  @Inject constructor(private val categoriesRepository: CategoriesRepository): GetDisplayCategoriesUseCase {
    override fun invoke(): Single<List<Category>> {
       return categoriesRepository.getCategories()
    }

}