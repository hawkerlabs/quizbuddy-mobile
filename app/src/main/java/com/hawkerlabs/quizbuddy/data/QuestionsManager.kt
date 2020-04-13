package com.hawkerlabs.quizbuddy.data

import com.hawkerlabs.quizbuddy.data.model.Option
import com.hawkerlabs.quizbuddy.data.model.Question

object QuestionsManager{
    private  val questions = mutableListOf<Question>()
    private var questionsIterator : Iterator<Question> = questions.iterator()


    fun getQuestions(): List<Question> = questions

    fun getQuestionsIterator(): Iterator<Question> = questionsIterator





    fun initialize(){
        var options  = mutableSetOf<Option>()
        options.add(Option(1, "1st question", false))
        options.add(Option(2, "2nd question", false))


        var options1  = mutableSetOf<Option>()
        options1.add(Option(1, "1st question", false))
        options1.add(Option(2, "2nd question", true))
        options1.add(Option(3, "3rd question", false))
        options1.add(Option(4, "4tt question", false))


        questions.add(Question("someId1", "WHat is something?", options))
        questions.add(Question("someId2", "WHat is something 2?", options1))

        questionsIterator = questions.iterator()
    }

     fun getAllQuestions(): List<Question> {


        return questions
    }


}