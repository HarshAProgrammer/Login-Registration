package com.example.userregistration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class LoginActivity : AppCompatActivity() {

    companion object{
        val KEY1 = "com.example.userregistration.LoginActivity.mail"
        val KEY2 = "com.example.userregistration.LoginActivity.username"
    }

    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val signInButton = findViewById<Button>(R.id.btnSignIn)
        val username = findViewById<TextInputEditText>(R.id.tieUsernameLogin)
        val signInText = findViewById<TextView>(R.id.tvRegister)

        signInText.setOnClickListener {

            val intentRegister =  Intent(applicationContext,MainActivity::class.java)
            startActivity(intentRegister)
        }


        signInButton.setOnClickListener {
            val uniqueId = username.text.toString()
            if (uniqueId.isNotEmpty()) {
                readData(uniqueId)

            } else {
                Toast.makeText(applicationContext, "Please enter a Username", Toast.LENGTH_LONG)
                    .show()
            }
        }

    }

    private fun readData(uniqueID: String) {
        database = FirebaseDatabase.getInstance().getReference("Users")
        database.child(uniqueID).get().addOnSuccessListener {

            if (it.exists()) {
                val email = it.child("email").value.toString()
                val username = it.child("username").value.toString()

                val intentWelcome = Intent(applicationContext,WelcomeActivity::class.java)
                intentWelcome.putExtra(KEY1,email)
                intentWelcome.putExtra(KEY2,username)

                startActivity(intentWelcome)

            }

        }.addOnFailureListener {
            Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG)

        }

    }
}