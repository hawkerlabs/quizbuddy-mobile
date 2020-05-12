package com.hawkerlabs.quizbuddy.domain.category

import com.hawkerlabs.quizbuddy.application.core.UseCase
import com.hawkerlabs.quizbuddy.data.api.model.Category
import io.reactivex.Single

interface GetDisplayCategoriesUseCase : UseCase<List<Category>> {
    override operator fun invoke(): Single<List<Category>>

}