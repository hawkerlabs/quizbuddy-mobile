package com.hawkerlabs.quizbuddy.data.repository

import com.hawkerlabs.quizbuddy.data.api.model.question.QuestionDTO
import io.reactivex.Single


@Deprecated("Will be removed for future release, use the QuizRepository instead")
interface QuestionsRepository {

    fun getQuestionsByCategory(categoryId : String) : Single<List<QuestionDTO>>
}