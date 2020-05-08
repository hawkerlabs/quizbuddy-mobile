package com.hawkerlabs.quizbuddy.domain.question

import com.hawkerlabs.quizbuddy.data.api.model.question.Data
import io.reactivex.Single

interface GetQuestionsByCategoryUseCase{



    fun invoke(categoryId : String): Single<List<Data>>
}