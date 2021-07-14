package com.example.moamoadamoa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView

class MypageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)

        val backbtn:ImageButton = findViewById(R.id.btn_back)
        backbtn.setOnClickListener{
            finish()
        }
        val btnlogout:ImageButton = findViewById(R.id.btn_logout)
        btnlogout.setOnClickListener{
            val intent = Intent(this@MypageActivity,LoginActivity::class.java)
            startActivity(intent)
        }
        val myProduct:ImageView = findViewById(R.id.imageView4)
        myProduct.setOnClickListener {
            val intent = Intent(this@MypageActivity,MainActivity::class.java)
            startActivity(intent)
        }

    }
}