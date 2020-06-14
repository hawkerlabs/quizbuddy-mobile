package com.hawkerlabs.quizbuddy.data.repository.impl

import com.hawkerlabs.quizbuddy.data.api.SubjectsApi
import com.hawkerlabs.quizbuddy.data.model.Subject
import com.hawkerlabs.quizbuddy.data.repository.SubjectsRepository
import io.reactivex.Single
import javax.inject.Inject

class SubjectsRepositoryImpl @Inject constructor(private val subjectsApi: SubjectsApi): SubjectsRepository {
    override fun getSubjectByCourse(courseId: String): Single<List<Subject>> {
        return subjectsApi.getSubjectsByCourse(courseId).map {
            it.subjects
        }
    }

}