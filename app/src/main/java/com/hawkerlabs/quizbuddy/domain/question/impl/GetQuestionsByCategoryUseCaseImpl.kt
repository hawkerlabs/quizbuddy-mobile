package com.hawkerlabs.quizbuddy.domain.question.impl

import com.hawkerlabs.quizbuddy.data.api.model.question.Data
import com.hawkerlabs.quizbuddy.data.repository.QuestionsRepository
import com.hawkerlabs.quizbuddy.domain.question.GetQuestionsByCategoryUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetQuestionsByCategoryUseCaseImpl @Inject constructor(private val questionsRepository : QuestionsRepository) : GetQuestionsByCategoryUseCase {


    /**
     *
     */
    override fun invoke(categoryId: String): Single<List<Data>> {
       return questionsRepository.getQuestionsByCategory(categoryId)
    }

}