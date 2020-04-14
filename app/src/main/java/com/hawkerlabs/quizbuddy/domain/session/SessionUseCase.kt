package com.hawkerlabs.quizbuddy.domain.session

import com.hawkerlabs.quizbuddy.data.model.Question
import com.hawkerlabs.quizbuddy.data.model.SessionState
import io.reactivex.Single

interface SessionUseCase {

//     fun getAllQuestions(): Single<List<Question>>



     fun getNextQuestion(): Single<Question>

     fun onAnswerSubmit(selectedId : Int )


    fun getSessionState(): Single<SessionState>

}