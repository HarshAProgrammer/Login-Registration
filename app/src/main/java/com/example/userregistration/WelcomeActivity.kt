package com.example.userregistration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val email = intent.getStringExtra(LoginActivity.KEY1)
        val username = intent.getStringExtra(LoginActivity.KEY2)

        val emailText = findViewById<TextView>(R.id.tvEmail)
        val usernameText = findViewById<TextView>(R.id.tvUsername)

        emailText.text = email
        usernameText.text = username

    }
}