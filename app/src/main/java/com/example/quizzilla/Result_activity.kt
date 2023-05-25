package com.example.quizzilla

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Result_activity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val tv_name=findViewById<TextView>(R.id.tv_name)
        val tv_score=findViewById<TextView>(R.id.tv_score)
        val ruser_name=intent.getStringExtra(AllQuestions.user_name)
        val rtotal_question=intent.getStringExtra(AllQuestions.total_question)
        val rtotal_correct_answer=intent.getStringExtra(AllQuestions.total_correct_answer)

        tv_name.text=ruser_name
        tv_score.text="You scored ${rtotal_correct_answer} out of ${rtotal_question}"
    }
}