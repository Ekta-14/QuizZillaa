package com.example.quizzilla

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val name=findViewById<EditText>(R.id.et_name)
        val submit=findViewById<Button>(R.id.btn_submit)
        submit.setOnClickListener {

            if(name.text.isNotEmpty())
            {
                val intent = Intent(this, QuizQuestionActivity::class.java)
                intent.putExtra(AllQuestions.user_name,name.text.toString())
                startActivity(intent)
                finish()//to finish the previous activity
            }
            else
                Toast.makeText(this,"Please Enter your name",Toast.LENGTH_SHORT).show()
        }
    }
}
