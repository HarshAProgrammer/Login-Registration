package com.example.userregistration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.btnSignUp)
        val etUsername = findViewById<TextInputEditText>(R.id.tieUsername)
        val etEmail = findViewById<TextInputEditText>(R.id.tieEmail)
        val etPassword = findViewById<TextInputEditText>(R.id.tiePassword)
        val tvAlready = findViewById<TextView>(R.id.tvAlreadyUser)

        tvAlready.setOnClickListener {
            val intentAlreadyUser=  Intent(applicationContext,LoginActivity::class.java)
            startActivity(intentAlreadyUser)
        }


        button.setOnClickListener {

            val username = etUsername.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            val user = User(username,email, password)
            database = FirebaseDatabase.getInstance().getReference("Users")
            database.child(username).setValue(user).addOnSuccessListener {
                etEmail.text?.clear()
                etUsername.text?.clear()
                etPassword.text?.clear()
                Toast.makeText(applicationContext,"User Registration Successful",Toast.LENGTH_LONG).show()
            }.addOnFailureListener {
                Toast.makeText(applicationContext,"User Registration Failed",Toast.LENGTH_LONG).show()

            }


        }


    }

}
