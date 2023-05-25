package com.example.quizzilla

object AllQuestions
{
    const val user_name="user_name"
    const val total_question="total_questions"
    const val total_correct_answer="correct answer"

    fun getQuestionsList() :ArrayList<QuestionData>{
        val questionList = ArrayList<QuestionData>()

        val ques1 = QuestionData(
            1,
            "What country does this flag represents?",
            R.drawable.india,
            "Argentina",
            "Japan",
            "Malaysia",
            "India",
            4
        )
        questionList.add(ques1)

        val ques2 = QuestionData(
            2,
            "What country does this flag represents?",
            R.drawable.japan,
            "Austria",
            "Japan",
            "Germany",
            "India",
            2
        )
        questionList.add(ques2)

        val ques3 = QuestionData(
            3,
            "What country does this flag represents?",
            R.drawable.england,
            "Australia",
            "Japan",
            "Pakistan",
            "England",
            4

        )
        questionList.add(ques3)

        val ques4 = QuestionData(
            1,
            "What country does this flag represents?",
            R.drawable.france,
            "Kazakistan",
            "France",
            "Dubai",
            "Nepal",
            2
        )
        questionList.add(ques4)

        val ques5 = QuestionData(
            1,
            "What country does this flag represents?",
            R.drawable.america,
            "America",
            "Turkey",
            "China",
            "Japan",
            1
        )
        questionList.add(ques5)

        return questionList

    }


}