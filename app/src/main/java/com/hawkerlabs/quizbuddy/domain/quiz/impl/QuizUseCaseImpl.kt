package com.hawkerlabs.quizbuddy.domain.quiz.impl

import com.hawkerlabs.quizbuddy.data.api.model.question.QuestionDTO
import com.hawkerlabs.quizbuddy.data.repository.Mode
import com.hawkerlabs.quizbuddy.data.repository.QuizRepository
import com.hawkerlabs.quizbuddy.domain.quiz.QuizUseCase
import io.reactivex.Single
import javax.inject.Inject


/**
 * 
 */
class QuizUseCaseImpl @Inject constructor(private val quizRepository : QuizRepository): QuizUseCase {
    override fun invoke(mode: Mode): Single<List<QuestionDTO>> {
        return when (mode) {
            is Mode.Category -> quizRepository.getQuestionsByCategory(mode.categoryId)
            is Mode.Subject -> quizRepository.getQuestionsBySubject(mode.subjectId)
        }
    }
}