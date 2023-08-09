package com.example.layoutpractice.ui.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.layoutpractice.R
import com.example.layoutpractice.service.MemberServiceImpl

class SignInActivity : AppCompatActivity() {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private val memberService by lazy {
        MemberServiceImpl.getInstance()
    }

    var curId : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val id = findViewById<EditText>(R.id.idText)
        val pw = findViewById<EditText>(R.id.pwText)
        val login = findViewById<Button>(R.id.loginButton)
        val reg = findViewById<Button>(R.id.regButton)

        setResultSignUp()

        login.setOnClickListener {
            if (id.text.isEmpty() || pw.text.isEmpty()) {
                Toast.makeText(this.applicationContext,"아이디 또는 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
            else {
                curId = memberService.login(id.text.toString(), pw.text.toString())

                if (curId.isNullOrEmpty()) {
                    Toast.makeText(this.applicationContext,"올바르지 않은 아이디 또는 비밀번호 입니다.", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(this.applicationContext,"로그인에 성공 하였습니다.", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("userid", curId)
                    startActivity(intent)
                }

            }
        }

        reg.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

    private fun setResultSignUp(){
        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if (result.resultCode == Activity.RESULT_OK){
                val id = findViewById<EditText>(R.id.idText)
                val pw = findViewById<EditText>(R.id.pwText)

                val idText = result.data?.getStringExtra("id") ?: ""
                val passwordText = result.data?.getStringExtra("password")?:""
                id.setText(idText)
                pw.setText(passwordText)
            }
        }
    }
}