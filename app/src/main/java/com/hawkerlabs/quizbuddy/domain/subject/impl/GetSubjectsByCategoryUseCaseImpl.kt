package com.hawkerlabs.quizbuddy.domain.subject.impl

import com.hawkerlabs.quizbuddy.data.model.Subject
import com.hawkerlabs.quizbuddy.data.repository.SubjectsRepository
import com.hawkerlabs.quizbuddy.domain.subject.GetSubjectsByCategoryUseCase
import io.reactivex.Single
import javax.inject.Inject


class GetSubjectsByCategoryUseCaseImpl @Inject constructor(private val subjectsRepository: SubjectsRepository) :
    GetSubjectsByCategoryUseCase {


    /**
     * Return subjects only which have question count greater than 10
     */
    override fun invoke(courseId: String): Single<List<Subject>> {

        return subjectsRepository.getSubjectByCourse(courseId)
//        return subjectsRepository.getSubjectByCourse(courseId).map {
//            it.filter { subject ->
//                subject.questions.count() > 10
//            }
//        }
    }

}