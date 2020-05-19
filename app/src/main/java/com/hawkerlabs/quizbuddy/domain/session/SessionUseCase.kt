package com.hawkerlabs.quizbuddy.domain.session

import com.hawkerlabs.quizbuddy.data.api.model.question.Data
import com.hawkerlabs.quizbuddy.data.model.Question
import com.hawkerlabs.quizbuddy.data.model.Session
import com.hawkerlabs.quizbuddy.data.model.Result

import io.reactivex.Single

interface SessionUseCase {


    fun initSession(questions: List<Data>)

//    fun initSessionByCategory(categoryId : String)

     fun getNextQuestion(): Single<Data>

     fun onAnswerSubmit(selectedId : Int, currentQuestion : Question)



    fun getTestResults() : Single<Result>

}