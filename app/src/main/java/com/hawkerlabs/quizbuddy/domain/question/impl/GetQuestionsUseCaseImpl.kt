package com.hawkerlabs.quizbuddy.domain.question.impl

import com.hawkerlabs.quizbuddy.data.model.Option
import com.hawkerlabs.quizbuddy.data.model.Question
import com.hawkerlabs.quizbuddy.domain.question.GetQuestionsUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetQuestionsUseCaseImpl @Inject constructor() : GetQuestionsUseCase{


   private val questions = mutableListOf<Question>()



    init {
        var options  = mutableSetOf<Option>()
        options.add(Option(1, "1st question", false))
        options.add(Option(2, "2nd question", false))


        var options1  = mutableSetOf<Option>()
        options1.add(Option(1, "1st question", false))
        options1.add(Option(2, "2nd question", true))
        options1.add(Option(3, "1st question", false))
        options1.add(Option(4, "1st question", false))


        questions.add(Question("someId1", "WHat is something?", options))
        questions.add(Question("someId2", "WHat is something 2?", options1))
    }


    /**
     *
     */
    override fun invoke(): Single<List<Question>> {
       return Single.just(questions)
    }

}