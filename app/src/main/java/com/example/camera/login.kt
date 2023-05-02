package com.example.camera

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

//import android.widget.Button

class login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
//        val name = findViewById<EditText>(R.id.name_input)
        val pass = findViewById<EditText>(R.id.password_input)
        val email = findViewById<EditText>(R.id.email_input)
        val register= findViewById<Button>(R.id.btn)
        val signup=findViewById<TextView>(R.id.signup)

//        val FirebaseAuth auth;
        val auth = Firebase.auth
        fun validateuser(){
            Toast.makeText(this,"Ok!! inside validate user",Toast.LENGTH_SHORT).show()
//            val value1=name.text.toString()
            val value2=pass.text.toString()
            val value3=email.text.toString()

            if(value2.isEmpty() || value3.isEmpty()){
                Toast.makeText(this,"Please eneter a value",Toast.LENGTH_SHORT).show()
            }
        }

        //        auth = FirebaseAuth.getInstance();
        fun login(){
            val password=pass.text.toString()
            val emailid=email.text.toString()
            auth.signInWithEmailAndPassword(emailid, password).addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Successfully LoggedIn", Toast.LENGTH_SHORT).show()
                    intent = Intent(applicationContext, home::class.java)
                    startActivity(intent)
                } else
                    Toast.makeText(this, "Log In failed ", Toast.LENGTH_SHORT).show()
            }
        }
        register.setOnClickListener {
//            validateuser()
            login()
        }
//using Intent to go to the Sign-Up page to help user Create and Acoount and Register
        signup.setOnClickListener {
//            Toast.makeText(this,"Ok",Toast.LENGTH_SHORT).show()
            intent = Intent(applicationContext,Signup::class.java)
            startActivity(intent)
        }



    }
}