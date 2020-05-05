package com.hawkerlabs.quizbuddy.domain.session.impl

import android.annotation.SuppressLint
import com.hawkerlabs.quizbuddy.application.core.dagger.module.SCHEDULER_IO
import com.hawkerlabs.quizbuddy.application.core.dagger.module.SCHEDULER_MAIN_THREAD
import com.hawkerlabs.quizbuddy.data.QuestionsManager
import com.hawkerlabs.quizbuddy.data.api.model.Category
import com.hawkerlabs.quizbuddy.data.api.model.question.Question
import com.hawkerlabs.quizbuddy.data.model.Session
import com.hawkerlabs.quizbuddy.domain.question.GetQuestionsByCategoryUseCase
import com.hawkerlabs.quizbuddy.domain.session.SessionUseCase
import com.hawkerlabs.quizbuddy.presentation.category.viewmodel.CategoriesListItemViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Named

class SessionUseCaseImpl @Inject constructor(
    private val getQuestionsByCategoryUseCase: GetQuestionsByCategoryUseCase,
    @Named(SCHEDULER_IO) val subscribeOnScheduler: Scheduler,
    @Named(SCHEDULER_MAIN_THREAD) val observeOnScheduler: Scheduler
) : SessionUseCase {

    private var questions = listOf<Question>()

    private var questionsIterator : Iterator<Question> = questions.iterator()
    private var correctAnswerCount: Int = 0


    var currentQuestion : Question? = null

    /**
     *
     */
    override fun getNextQuestion(): Single<Question> {

        if (questionsIterator.hasNext()) {
            var question = questionsIterator.next()
            currentQuestion = question
            return Single.just(question)
        }

    return Single.just(Question())
//        return Single.just(Question("", "", emptySet(), -1))

    }


    /**
     * Update the correct answer count
     */
    override fun onAnswerSubmit(selectedId: Int) {

        if (QuestionsManager.currentQuestion?.correctAnswer == selectedId) {
            QuestionsManager.correctAnswerCount++
        }
    }

    override fun getSessionState(): Single<Session> {
        TODO("Not yet implemented")
    }


    /**
     *
     */
    override fun initSession() {


        QuestionsManager.initialize()
        correctAnswerCount = 0
    }

    @SuppressLint("CheckResult")
    override fun initSessionByCategory(categoryId: String) {
        getQuestionsByCategoryUseCase.invoke(categoryId).subscribeOn(subscribeOnScheduler)
            .observeOn(observeOnScheduler)
            .subscribe(this::onResponse, this::onError)
    }


    private fun onResponse(categories: List<Question>) {
//        categories.map { item ->
//            _categoriesList.add(CategoriesListItemViewModel(item))
//        }
//
//        _categoriesListItemViewModel.value = _categoriesList
    }



    private fun onError(error: Throwable) {
        error
    }

}