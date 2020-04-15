package com.hawkerlabs.quizbuddy.data

import com.hawkerlabs.quizbuddy.data.model.Option
import com.hawkerlabs.quizbuddy.data.model.Question

object QuestionsManager{
    private  val questions = mutableListOf<Question>()
    private var questionsIterator : Iterator<Question> = questions.iterator()
    var currentQuestion : Question? = null

    var correctAnswerCount : Int = 0

    fun getQuestions(): List<Question> = questions



    fun getQuestionsIterator(): Iterator<Question> = questionsIterator





    fun initialize(){
        questions.clear() //just mocking
        var options  = mutableSetOf<Option>()
        options.add(Option(1, "Milan", false))
        options.add(Option(2, "Pisa", false))
        options.add(Option(3, "Rome", false))
        options.add(Option(4, "Turin", false))


        var options1  = mutableSetOf<Option>()
        options1.add(Option(1, "Buenos Aires", false))
        options1.add(Option(2, "Mendoza", false))
        options1.add(Option(3, "Salta", false))
        options1.add(Option(4, "Ushuaia", false))

        var options3 = mutableSetOf<Option>()
        options3.add(Option(1, "Scott Morrison", false))
        options3.add(Option(2, "Malcolm Turnbull", false))
        options3.add(Option(3, "Tony Abbott", false))
        options3.add(Option(4, "Julia Gillard", false))


        questions.add(Question("someId1", "Which Italian city has the leaning tower?", options, 2))
        questions.add(Question("someId2", "What is the capital city of Argentina?", options1, 1))


        questions.add(Question("someId3", "Who is the current Prime Minister of Australia?", options3, 1))

        questionsIterator = questions.iterator()
        correctAnswerCount = 0
    }






     fun getAllQuestions(): List<Question> {


        return questions
    }


}