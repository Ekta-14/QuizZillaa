package com.example.quizzilla

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class QuizQuestionActivity : AppCompatActivity(), View.OnClickListener//its an interface so neeche vala fun impelemt hoga
{
    private var tv_question:TextView?=null
    private var iv_flag:ImageView?=null
    private var pb_progress:ProgressBar?=null
    private var tv_progress:TextView?=null
    private var tv_option1:TextView?=null
    private var tv_option2:TextView?=null
    private var tv_option3:TextView?=null
    private var tv_option4:TextView?=null
    private var btn_submit:Button?=null

    private var currentPositionOfQuestion=1
    private var currentSelectedOptionPosition=0
    private val questionList=AllQuestions.getQuestionsList()
    private var count=0

    private var muser_name:String?=null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        tv_question=findViewById(R.id.tv_question)
        iv_flag=findViewById(R.id.iv_flag)
        pb_progress=findViewById(R.id.pb_progress)
        tv_progress=findViewById(R.id.tv_progress)
        tv_option1=findViewById(R.id.tv_option1)
        tv_option2=findViewById(R.id.tv_option2)
        tv_option3=findViewById(R.id.tv_option3)
        tv_option4=findViewById(R.id.tv_option4)
        btn_submit=findViewById(R.id.btn_submit)

        tv_option1?.setOnClickListener(this)
        tv_option2?.setOnClickListener(this)
        tv_option3?.setOnClickListener(this)
        tv_option4?.setOnClickListener(this)
        btn_submit?.setOnClickListener(this)

        setQuestion()

        muser_name=intent.getStringExtra(AllQuestions.user_name)
    }

    @SuppressLint("SetTextI18n")
    private fun setQuestion()
    {
        val question = questionList[currentPositionOfQuestion - 1]

        tv_question?.text = question.question
        iv_flag?.setImageResource(question.image)
        pb_progress?.progress = currentPositionOfQuestion
        tv_progress?.text = "${currentPositionOfQuestion}/${pb_progress?.max}"

        tv_option1?.text = question.option1
        tv_option2?.text = question.option2
        tv_option3?.text = question.option3
        tv_option4?.text = question.option4
    }


    //function for default state of option
    private fun defaultOptionBorder()
    {
        tv_option1?.let{
            it.background=ContextCompat.getDrawable(this,R.drawable.default_option_border)
            it.setTextColor(Color.parseColor("#7A8089"))
        }
        tv_option2?.let {
            it.background=ContextCompat.getDrawable(this,R.drawable.default_option_border)
            it.setTextColor(Color.parseColor("#7A8089"))
        }
        tv_option3?.let {
            it.background=ContextCompat.getDrawable(this,R.drawable.default_option_border)
            it.setTextColor(Color.parseColor("#7A8089"))
        }
        tv_option4?.let {
            it.background=ContextCompat.getDrawable(this,R.drawable.default_option_border)
            it.setTextColor(Color.parseColor("#7A8089"))
        }

        //another method
//        val option = ArrayList<TextView>()
//        tv_option1?.let { option.add(it) }
//        tv_option2?.let { option.add(it) }
//        tv_option3?.let { option.add(it) }
//        tv_option4?.let { option.add(it) }
//
//        for (o in option){
//            o.background = ContextCompat.getDrawable(this, R.drawable.default_option_border)
//            o.setTextColor(Color.parseColor("#7A8089"))
//        }
    }



    //function for selected option border
    private fun selectedOption(tv:TextView,selectedOptionPosition:Int)
    {
        defaultOptionBorder()
        currentSelectedOptionPosition=selectedOptionPosition
        tv.setTextColor(Color.parseColor("#10002b"))
        tv.background=ContextCompat.getDrawable(this,R.drawable.option_selected_border)
    }

    override fun onClick(view: View?)
    {
        when (view?.id)
        {
            R.id.tv_option1 -> tv_option1?.let { selectedOption(it, 1) }
            R.id.tv_option2 -> tv_option2?.let { selectedOption(it, 2) }
            R.id.tv_option3 -> tv_option3?.let { selectedOption(it, 3) }
            R.id.tv_option4 -> tv_option4?.let { selectedOption(it, 4) }

           R.id.btn_submit -> if(btn_submit?.text=="SUBMIT")
                              {
                                  if(currentSelectedOptionPosition==0)
                                      Toast.makeText(this,"Please select an option",Toast.LENGTH_SHORT).show()
                                  else
                                  {
                                      if (currentSelectedOptionPosition == questionList[currentPositionOfQuestion - 1].answer)
                                      {
                                          correctAnswerColor(currentSelectedOptionPosition)
                                          count++
                                      }
                                      else
                                      {
                                          wrongAnswerColor(currentSelectedOptionPosition)
                                          correctAnswerColor(questionList[currentPositionOfQuestion - 1].answer)
                                      }
                                      if(currentPositionOfQuestion==questionList.size)
                                          btn_submit?.text="RESULT"
                                      else
                                          btn_submit?.text="NEXT"

                                      currentSelectedOptionPosition=0
                                  }
                              }


                             else if(btn_submit?.text=="NEXT")
                              {
                                     currentPositionOfQuestion++
                                     setQuestion()
                                     btn_submit?.text="SUBMIT"
                                     defaultOptionBorder()
                             }


                             else if(btn_submit?.text=="RESULT")
                              {
                                  muser_name=intent.getStringExtra(AllQuestions.user_name)
                                val intent=Intent(this,Result_activity::class.java)
                                  intent.putExtra(AllQuestions.user_name,muser_name)
                                  intent.putExtra(AllQuestions.total_correct_answer,count.toString())
                                  intent.putExtra(AllQuestions.total_question, questionList.size.toString())
                                  startActivity(intent)
                                  finish()
                              }
        }
    }

    private fun correctAnswerColor(i:Int)
    {
        when(i)
        {
            1 -> tv_option1?.background = ContextCompat.getDrawable(this, R.drawable.answer_color)
            2 -> tv_option2?.background = ContextCompat.getDrawable(this, R.drawable.answer_color)
            3 -> tv_option3?.background = ContextCompat.getDrawable(this, R.drawable.answer_color)
            4 -> tv_option4?.background = ContextCompat.getDrawable(this, R.drawable.answer_color)
            else -> Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show()
        }
    }
    private fun wrongAnswerColor(i:Int)
    {
        when(i)
        {
            1 -> tv_option1?.background = ContextCompat.getDrawable(this, R.drawable.wrong_option_color)
            2 -> tv_option2?.background = ContextCompat.getDrawable(this, R.drawable.wrong_option_color)
            3 -> tv_option3?.background = ContextCompat.getDrawable(this, R.drawable.wrong_option_color)
            4 -> tv_option4?.background = ContextCompat.getDrawable(this, R.drawable.wrong_option_color)
            else -> Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show()
        }
    }
}