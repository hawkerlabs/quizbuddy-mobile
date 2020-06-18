package com.hawkerlabs.quizbuddy.data.repository

import com.hawkerlabs.quizbuddy.data.model.Subject
import io.reactivex.Single

interface SubjectsRepository{
    fun getSubjectByCourse(courseId : String) : Single<List<Subject>>
}