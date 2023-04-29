package com.example.camera

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Signup : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val regname = findViewById<EditText>(R.id.register_name_input)
        val regemail = findViewById<EditText>(R.id.register_email_input)
        val regpass = findViewById<EditText>(R.id.register_password_input)
        val regpassconfirm = findViewById<EditText>(R.id.confirm_password_input)
        val register = findViewById<Button>(R.id.btn2)

//      setting up a reference to the Firebase Authentication instance
        auth = Firebase.auth

        //      validating the response we got from the user.
        fun validatenewuser(){
            val reg1 = regname.text.toString()
            val reg2 = regemail.text.toString()
            val reg3 = regpass.text.toString()
            val reg4 = regpassconfirm.text.toString()

            //           confirms weather earch of the field is properly filed and nothing is empty
            fun isEmpty(){
                if(reg1.isEmpty()|| reg2.isEmpty() || reg3.isEmpty()|| reg4.isEmpty()){
                    Toast.makeText(this,"Option Can't left Blank!!",Toast.LENGTH_SHORT).show()
                }
            }

            //            Checks wheather or not the password entered is same
            fun pwdmissmatch(){
                if(reg3!= reg4){
                    Toast.makeText(this,"Password don't match..",Toast.LENGTH_SHORT).show()
                }
            }

            auth.createUserWithEmailAndPassword(reg2,reg3).addOnCompleteListener(this){
                if(it.isSuccessful){
                    Toast.makeText(this,"Sucessfull",Toast.LENGTH_SHORT).show()
                    intent= Intent(applicationContext,login::class.java)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this,"Sign Up Failed!!",Toast.LENGTH_SHORT).show()
                }
            }

        }

        register.setOnClickListener {
            validatenewuser()
            Toast.makeText(this,"Show",Toast.LENGTH_SHORT).show()
        }

    }
}