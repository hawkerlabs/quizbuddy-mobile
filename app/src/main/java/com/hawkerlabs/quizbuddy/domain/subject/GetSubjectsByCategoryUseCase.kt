package com.hawkerlabs.quizbuddy.domain.subject

import com.hawkerlabs.quizbuddy.data.model.Subject
import io.reactivex.Single


interface GetSubjectsByCategoryUseCase{


    fun invoke(courseId : String): Single<List<Subject>>

}
