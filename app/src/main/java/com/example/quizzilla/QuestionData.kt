package com.example.quizzilla

data class QuestionData(
    val id:Int,
    val question:String,
    val image:Int,
    val option1:String,
    val option2:String,
    val option3:String,
    val option4:String,
    val answer:Int
    )
