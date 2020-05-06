package com.hawkerlabs.quizbuddy.domain.session

import com.hawkerlabs.quizbuddy.data.api.model.question.Question
import com.hawkerlabs.quizbuddy.data.model.Session
import io.reactivex.Single

interface SessionUseCase {


    fun initSession(questions: List<Question>)

    fun initSessionByCategory(categoryId : String)

     fun getNextQuestion(): Single<Question>

     fun onAnswerSubmit(selectedId : Int )


    fun getSessionState(): Single<Session>

}