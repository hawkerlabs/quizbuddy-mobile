package com.hawkerlabs.quizbuddy.domain.session.impl

import com.hawkerlabs.quizbuddy.data.model.Result
import com.hawkerlabs.quizbuddy.data.model.Score
import com.hawkerlabs.quizbuddy.domain.session.GetScoreUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetScoreUseCaseImpl  @Inject constructor() : GetScoreUseCase{

    /**
     *
     */
    override fun invoke(result: Result): Single<Score> {
        val count = result.correctAnswers.size + result.inCorrectAnswers.size
        val score = (result.correctAnswers.size.toDouble()/count)*100


        return Single.just(  Score("", score.toInt().toString()))
    }

}
