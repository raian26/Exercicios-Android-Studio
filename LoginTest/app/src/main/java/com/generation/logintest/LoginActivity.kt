package com.generation.logintest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.generation.logintest.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.installations.Utils
import com.google.firebase.ktx.Firebase

class LoginActivity :AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
    binding.buttonLogin.setOnClickListener {
        val email  = binding.emailLogin.text.toString()
        val password = binding.passwordLogin.text.toString()
        if (email.isEmpty() && password.isEmpty()) {
            Toast.makeText(this, "Prencha os campos!", Toast.LENGTH_SHORT).show()
        } else {
            login(email, password)
        }
    }
        binding.buttonRegister.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }


    }

    fun login(email:String, password:String){

        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this){
           if(it.isSuccessful){
               Toast.makeText(this,"Login efetuado com sucesso!", Toast.LENGTH_SHORT).show()
               val intent = Intent(this,MainActivity::class.java)
               startActivity(intent)
           }else{
               Toast.makeText(this,"Email ou senha inválida", Toast.LENGTH_SHORT).show()
           }
       }
    }

}

