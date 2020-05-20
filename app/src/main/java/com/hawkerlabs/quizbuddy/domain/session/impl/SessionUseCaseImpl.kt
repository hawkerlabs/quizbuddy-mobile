package com.hawkerlabs.quizbuddy.domain.session.impl

import android.annotation.SuppressLint
import com.hawkerlabs.quizbuddy.application.core.dagger.module.SCHEDULER_IO
import com.hawkerlabs.quizbuddy.application.core.dagger.module.SCHEDULER_MAIN_THREAD
import com.hawkerlabs.quizbuddy.data.api.model.question.Data
import com.hawkerlabs.quizbuddy.data.model.CurrentQuestion
import com.hawkerlabs.quizbuddy.data.model.Question
import com.hawkerlabs.quizbuddy.data.model.Result
import com.hawkerlabs.quizbuddy.data.model.Session
import com.hawkerlabs.quizbuddy.domain.question.GetQuestionsByCategoryUseCase
import com.hawkerlabs.quizbuddy.domain.session.SessionUseCase
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Named

class SessionUseCaseImpl @Inject constructor(
    private val getQuestionsByCategoryUseCase: GetQuestionsByCategoryUseCase,
    @Named(SCHEDULER_IO) val subscribeOnScheduler: Scheduler,
    @Named(SCHEDULER_MAIN_THREAD) val observeOnScheduler: Scheduler
) : SessionUseCase {

    private var questions = arrayOf<Data>()

    private lateinit var questionsIterator : ListIterator<Data>
    private var correctAnswerCount: Int = 0

    private lateinit var result: Result

    var currentQuestion : Data? = null


    private var currentIndex = -1


    /**
     * Emit the the previous question
     */
    override fun getPreviousQuestion(): Single<CurrentQuestion> {

        currentIndex--
        val questionIndex = currentIndex + 1
        if(currentIndex >=  0 ){
            return Single.just(CurrentQuestion(questions[currentIndex], (questionIndex).toString().plus("/").plus(questions.size)))
        }

        return Single.just(CurrentQuestion(Data(), ""))

    }




    /**
     *  Emit the the next question
     */
    override fun getNextQuestion(): Single<CurrentQuestion> {



        currentIndex++
        val questionIndex = currentIndex + 1
        if(currentIndex < questions.size ){
            return Single.just(CurrentQuestion(questions[currentIndex], (questionIndex).toString().plus("/").plus(questions.size)))
        }

    return Single.just(CurrentQuestion(Data(), ""))

    }


    /**
     * Update the correct answer count
     */
    override fun onAnswerSubmit(selectedId: Int, currentQuestion : Question) {

        if (currentQuestion?.correctAnswer == selectedId) {

            result.correctAnswerCount++
            result.correctAnswers.add(currentQuestion)
        } else {
            result.inCorrectAnswers.add(currentQuestion)
        }
    }

    override fun finishTest() {
        currentIndex = -1
    }


    /**
     *
     */
    override fun getTestResults(): Single<Result> {
        return Single.just(result)
    }


    /**
     * Initialize test session
     */
    override fun initSession(questionSet: List<Data>) {
        currentIndex = -1
        questions = questionSet.toTypedArray()

//        questionsIterator = questions.listIterator()
        correctAnswerCount = 0

        result = Result( mutableSetOf<Question>(), mutableSetOf<Question>(), 0, false )
//        getNextQuestion()
    }





    private fun onError(error: Throwable) {
        error
    }

}