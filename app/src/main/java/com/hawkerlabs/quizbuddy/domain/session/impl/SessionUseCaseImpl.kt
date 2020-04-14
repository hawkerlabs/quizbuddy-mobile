package com.hawkerlabs.quizbuddy.domain.session.impl

import com.hawkerlabs.quizbuddy.data.QuestionsManager
import com.hawkerlabs.quizbuddy.data.model.Question
import com.hawkerlabs.quizbuddy.data.model.SessionState
import com.hawkerlabs.quizbuddy.domain.session.SessionUseCase
import io.reactivex.Single
import javax.inject.Inject

class SessionUseCaseImpl @Inject constructor(): SessionUseCase {

    private var questions = listOf<Question>()


    private var correctAnswerCount : Int = 0


    /**
     *
     */
    override fun getNextQuestion(): Single<Question> {

        if(QuestionsManager.getQuestionsIterator().hasNext()){
            var question = QuestionsManager.getQuestionsIterator().next()
            QuestionsManager.currentQuestion = question
            return Single.just(question)
        }

            return Single.just(Question("", "", emptySet(), -1))

    }


    /**
     * Update the correct answer count
     */
    override fun onAnswerSubmit( selectedId: Int) {

       if( QuestionsManager.currentQuestion?.correctAnswer == selectedId){
//           correctAnswerCount++
           QuestionsManager.correctAnswerCount++
       }
    }

    override fun getSessionState(): Single<SessionState> {
        TODO("Not yet implemented")
    }

}