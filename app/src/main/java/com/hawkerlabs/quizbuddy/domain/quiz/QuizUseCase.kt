package com.hawkerlabs.quizbuddy.domain.quiz

import com.hawkerlabs.quizbuddy.data.api.model.question.QuestionDTO
import com.hawkerlabs.quizbuddy.data.repository.Mode
import io.reactivex.Single

interface QuizUseCase {
    fun invoke(mode: Mode): Single<List<QuestionDTO>>
}