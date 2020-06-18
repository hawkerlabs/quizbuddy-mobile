package com.hawkerlabs.quizbuddy.data.repository

import com.hawkerlabs.quizbuddy.data.api.model.question.QuestionDTO
import io.reactivex.Single


sealed class Mode {

    data class Subject(val subjectId: String) : Mode()
    data class Category(val categoryId: String) : Mode()

}

interface QuizRepository {
    fun getQuestionsByCategory(categoryId: String): Single<List<QuestionDTO>>

    fun getQuestionsBySubject(subjectId: String): Single<List<QuestionDTO>>
}