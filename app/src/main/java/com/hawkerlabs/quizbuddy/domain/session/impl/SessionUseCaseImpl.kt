package com.hawkerlabs.quizbuddy.domain.session.impl

import com.hawkerlabs.quizbuddy.data.QuestionsManager
import com.hawkerlabs.quizbuddy.data.model.Question
import com.hawkerlabs.quizbuddy.data.model.SessionState
import com.hawkerlabs.quizbuddy.domain.session.SessionUseCase
import io.reactivex.Single
import javax.inject.Inject

class SessionUseCaseImpl @Inject constructor(): SessionUseCase {

    private var questions = listOf<Question>()
    private val questionsIterator : Iterator<Question>  = questions.iterator()





    /**
     *
     */
    override fun getNextQuestion(): Single<Question> {

        if(QuestionsManager.getQuestionsIterator().hasNext()){
            return Single.just(QuestionsManager.getQuestionsIterator().next())
        }

            return Single.just(Question("", "", emptySet()))

    }

    override fun getSessionState(): Single<SessionState> {
        TODO("Not yet implemented")
    }

}