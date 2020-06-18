package com.hawkerlabs.quizbuddy.data.repository.impl

import com.hawkerlabs.quizbuddy.data.api.QuestionsApi
import com.hawkerlabs.quizbuddy.data.api.QuizApi
import com.hawkerlabs.quizbuddy.data.api.model.question.QuestionDTO
import com.hawkerlabs.quizbuddy.data.repository.QuizRepository
import io.reactivex.Single
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val questionsApi: QuestionsApi,
    private val quizApi: QuizApi
) : QuizRepository {
    override fun getQuestionsByCategory(categoryId: String): Single<List<QuestionDTO>> {
        return questionsApi.getQuestionsByCategory(categoryId).map {
            it.data
        }
    }


    /**
     * Get questions by subject
     */
    override fun getQuestionsBySubject(subjectId: String): Single<List<QuestionDTO>> {
//        var data = mutableListOf<QuestionDTO>()
      return   quizApi.getQuestionsBySubject(subjectId).map {
           it.data.results
        }
    }

}