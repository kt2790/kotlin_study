package com.example.layoutpractice.ui.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.layoutpractice.R
import com.example.layoutpractice.service.MemberServiceImpl

class HomeActivity : AppCompatActivity() {

    private val memberService by lazy {
        MemberServiceImpl.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initializeView(intent.getStringExtra("userid") ?: "")
    }

    @SuppressLint("SetTextI18n")
    fun initializeView(userId: String) {

        if (userId.isNullOrEmpty()) {
            Toast.makeText(this.applicationContext,"존재하지 않는 아이디 입니다.", Toast.LENGTH_SHORT).show()
            finish()
        }

        val id = findViewById<TextView>(R.id.id_txt)
        val name = findViewById<TextView>(R.id.name_txt)
        val age = findViewById<TextView>(R.id.age_txt)
        val mbti = findViewById<TextView>(R.id.mbti_txt)
        val exit = findViewById<Button>(R.id.exit_button)

        id.text = "아이디 : $userId"
        name.text = "이름 : ${memberService.getMemberNameById(userId)}"
        age.text = "나이 : ${memberService.getMemberAgeById(userId)}"
        mbti.text = "MBTI : ${memberService.getMemberMbtiById(userId)}"

        exit.setOnClickListener {
            finish()
        }
    }
}