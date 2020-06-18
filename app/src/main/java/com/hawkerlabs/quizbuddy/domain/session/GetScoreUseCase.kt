package com.hawkerlabs.quizbuddy.domain.session

import io.reactivex.Single
import com.hawkerlabs.quizbuddy.data.model.Result
import com.hawkerlabs.quizbuddy.data.model.Score

interface GetScoreUseCase{

    fun invoke(result : Result): Single<Score>

}