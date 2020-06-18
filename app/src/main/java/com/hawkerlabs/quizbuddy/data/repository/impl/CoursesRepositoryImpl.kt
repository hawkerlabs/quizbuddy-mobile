package com.hawkerlabs.quizbuddy.data.repository.impl

import com.hawkerlabs.quizbuddy.data.api.CourseApi
import com.hawkerlabs.quizbuddy.data.model.Course
import com.hawkerlabs.quizbuddy.data.repository.CoursesRepository
import io.reactivex.Single
import javax.inject.Inject

class CoursesRepositoryImpl @Inject constructor(private val courseApi: CourseApi): CoursesRepository {
    override fun getFeaturedCourses(): Single<List<Course>> {

        return courseApi.getCourses().map {
            it.data
        }

    }
}