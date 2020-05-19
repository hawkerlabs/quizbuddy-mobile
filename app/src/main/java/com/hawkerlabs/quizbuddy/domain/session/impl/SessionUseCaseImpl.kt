package com.hawkerlabs.quizbuddy.domain.session.impl

import android.annotation.SuppressLint
import com.hawkerlabs.quizbuddy.application.core.dagger.module.SCHEDULER_IO
import com.hawkerlabs.quizbuddy.application.core.dagger.module.SCHEDULER_MAIN_THREAD
import com.hawkerlabs.quizbuddy.data.api.model.question.Data
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

    private var questions = listOf<Data>()

    private lateinit var questionsIterator : Iterator<Data>
    private var correctAnswerCount: Int = 0

    private lateinit var result: Result

    var currentQuestion : Data? = null

    /**
     *
     */
    override fun getNextQuestion(): Single<Data> {

        if (questionsIterator.hasNext()) {
            var question = questionsIterator.next()
            currentQuestion = question
            return Single.just(question)
        }

    return Single.just(Data())

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

        questions = questionSet
        questionsIterator = questions.iterator()
        correctAnswerCount = 0

        result = Result( mutableSetOf<Question>(), mutableSetOf<Question>(), 0, false )
    }

//    @SuppressLint("CheckResult")
//    override fun initSessionByCategory(categoryId: String) {
//        getQuestionsByCategoryUseCase.invoke(categoryId).subscribeOn(subscribeOnScheduler)
//            .observeOn(observeOnScheduler)
//            .subscribe(this::onResponse, this::onError)
//    }
//
//
//    private fun onResponse(categories: List<Data>) {
////        categories.map { item ->
////            _categoriesList.add(CategoriesListItemViewModel(item))
////        }
////
////        _categoriesListItemViewModel.value = _categoriesList
//    }



    private fun onError(error: Throwable) {
        error
    }

}