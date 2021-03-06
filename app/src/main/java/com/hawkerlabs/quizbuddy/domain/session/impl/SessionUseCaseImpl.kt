package com.hawkerlabs.quizbuddy.domain.session.impl

import com.hawkerlabs.quizbuddy.application.core.dagger.module.SCHEDULER_IO
import com.hawkerlabs.quizbuddy.application.core.dagger.module.SCHEDULER_MAIN_THREAD
import com.hawkerlabs.quizbuddy.data.api.model.question.QuestionDTO
import com.hawkerlabs.quizbuddy.data.model.CurrentQuestion
import com.hawkerlabs.quizbuddy.data.model.Question
import com.hawkerlabs.quizbuddy.data.model.Result
import com.hawkerlabs.quizbuddy.domain.session.SessionUseCase
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Named

class SessionUseCaseImpl @Inject constructor(
    @Named(SCHEDULER_IO) val subscribeOnScheduler: Scheduler,
    @Named(SCHEDULER_MAIN_THREAD) val observeOnScheduler: Scheduler
) : SessionUseCase {

    private var questions = arrayOf<QuestionDTO>()

    private var correctAnswerCount: Int = 0

    private lateinit var result: Result


    private var currentIndex = -1


    /**
     * Emit the the previous question
     */
    override fun getPreviousQuestion(): Single<CurrentQuestion> {

        currentIndex--
        val questionIndex = currentIndex + 1
        if (currentIndex >= 0) {
            return Single.just(
                CurrentQuestion(
                    questions[currentIndex],
                    (questionIndex).toString().plus("/").plus(questions.size),
                    currentIndex
                )
            )
        }

        return Single.just(CurrentQuestion(QuestionDTO(), "", -1))

    }


    /**
     *  Emit the the next question
     */
    override fun getNextQuestion(): Single<CurrentQuestion> {


        currentIndex++
        val questionIndex = currentIndex + 1
        if (currentIndex < questions.size) {
            return Single.just(
                CurrentQuestion(
                    questions[currentIndex],
                    (questionIndex).toString().plus("/").plus(questions.size),
                    currentIndex
                )
            )
        }

        return Single.just(CurrentQuestion(QuestionDTO(), "", -1))

    }


    /**
     * Update the correct answer count
     */
    override fun onAnswerSubmit(selectedId: Int, currentQuestion: Question) {

        if (currentQuestion?.correctAnswer == selectedId) {

            result.correctAnswerCount++
            result.correctAnswers.add(currentQuestion)
        } else {
            result.inCorrectAnswers.add(currentQuestion)
        }
    }


    /**
     *
     */
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
    override fun initSession(questionSet: List<QuestionDTO>) {
        currentIndex = -1
        questions = questionSet.toTypedArray()

        correctAnswerCount = 0


        result = Result(mutableSetOf<Question>(), mutableSetOf<Question>(), 0, false)
        result.correctAnswers.clear()
        result.inCorrectAnswers.clear()
    }


    private fun onError(error: Throwable) {
        error
    }

}