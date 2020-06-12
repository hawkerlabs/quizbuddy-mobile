package com.hawkerlabs.quizbuddy.domain.course.impl

import com.hawkerlabs.quizbuddy.data.model.Course
import com.hawkerlabs.quizbuddy.data.repository.CoursesRepository
import com.hawkerlabs.quizbuddy.domain.course.GetFeaturedCoursesUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetFeaturedCoursesUseCaseImpl  @Inject constructor(private val coursesRepository : CoursesRepository): GetFeaturedCoursesUseCase {
    override fun invoke(): Single<List<Course>> {
        return coursesRepository.getFeaturedCourses()
    }

}