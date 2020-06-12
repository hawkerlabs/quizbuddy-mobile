package com.hawkerlabs.quizbuddy.data.repository

import com.hawkerlabs.quizbuddy.data.model.Course
import io.reactivex.Single

interface CoursesRepository {
    fun getFeaturedCourses() : Single<List<Course>>

}