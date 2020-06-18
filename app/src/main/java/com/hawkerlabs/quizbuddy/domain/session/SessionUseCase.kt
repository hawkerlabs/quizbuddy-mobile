package com.hawkerlabs.quizbuddy.domain.session

import com.hawkerlabs.quizbuddy.data.api.model.question.QuestionDTO
import com.hawkerlabs.quizbuddy.data.model.CurrentQuestion
import com.hawkerlabs.quizbuddy.data.model.Question
import com.hawkerlabs.quizbuddy.data.model.Session
import com.hawkerlabs.quizbuddy.data.model.Result

import io.reactivex.Single

interface SessionUseCase {


    fun initSession(questions: List<QuestionDTO>)


    fun getPreviousQuestion(): Single<CurrentQuestion>

    fun getNextQuestion(): Single<CurrentQuestion>

    fun onAnswerSubmit(selectedId: Int, currentQuestion: Question)

    fun finishTest()

    fun getTestResults(): Single<Result>

}