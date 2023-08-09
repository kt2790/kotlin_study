package com.example.layoutpractice.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.layoutpractice.R
import com.example.layoutpractice.service.MemberServiceImpl

class SignUpActivity : AppCompatActivity() {
    private var memberService = MemberServiceImpl.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val name = findViewById<EditText>(R.id.nameEditText)
        val id = findViewById<EditText>(R.id.idEditText)
        val pw = findViewById<EditText>(R.id.pwEditText)
        val age = findViewById<EditText>(R.id.ageText)
        val mbti = findViewById<EditText>(R.id.mbtiText)

        val signup = findViewById<Button>(R.id.signupButton)

        signup.setOnClickListener {
            if (name.text.isEmpty() || id.text.isEmpty() || pw.text.isEmpty() || age.text.isEmpty() || mbti.text.isEmpty()) {
                Toast.makeText(this.applicationContext,"빈칸을 입력 해주세요.", Toast.LENGTH_SHORT).show()
            }
            else {
                if (memberService.addMember(id.text.toString(), pw.text.toString(), name.text.toString(), Integer.parseInt(age.text.toString()), mbti.text.toString())) {
                    Toast.makeText(this.applicationContext,"회원가입에 성공하였습니다.", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, SignInActivity::class.java)
                    intent.putExtra("id", id.text.toString())
                    intent.putExtra("password", pw.text.toString())
                    setResult(RESULT_OK, intent)
                    finish()
                }
                else {
                    Toast.makeText(this.applicationContext,"이미 존재하는 아이디 입니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}