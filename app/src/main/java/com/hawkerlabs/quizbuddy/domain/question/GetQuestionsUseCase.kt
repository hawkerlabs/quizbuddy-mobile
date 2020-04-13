package com.hawkerlabs.quizbuddy.domain.question

import com.hawkerlabs.quizbuddy.application.core.UseCase
import com.hawkerlabs.quizbuddy.data.model.Question
import io.reactivex.Single

interface GetQuestionsUseCase : UseCase<List<Question>> {
    override operator fun invoke(): Single<List<Question>>
}