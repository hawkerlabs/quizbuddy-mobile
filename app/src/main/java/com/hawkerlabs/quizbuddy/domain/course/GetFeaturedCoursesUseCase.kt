package com.hawkerlabs.quizbuddy.domain.course

import com.hawkerlabs.quizbuddy.application.core.UseCase
import com.hawkerlabs.quizbuddy.data.model.Course
import io.reactivex.Single

interface GetFeaturedCoursesUseCase : UseCase<List<Course>> {

    override operator fun invoke(): Single<List<Course>>
}