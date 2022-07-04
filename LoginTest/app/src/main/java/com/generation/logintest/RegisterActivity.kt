package com.generation.logintest

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.generation.logintest.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth


class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        binding.buttonRegister.setOnClickListener {
            val email = binding.emailRegister.text.toString()
            val password = binding.passwordRegister.text.toString()
            val confirmPassword = binding.passwordConfirm.text.toString()
            registerUser(email, password, confirmPassword)

        }

        binding.buttonVoltar.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    fun registerUser(email: String, password: String, confirmPassword: String) {
        if (email.isNotEmpty() &&
            password.isNotEmpty() &&
            confirmPassword.isNotEmpty()
        ) {
            if (password == confirmPassword) {
                val intent = Intent(this, LoginActivity::class.java)
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { Task ->
                        if (Task.isSuccessful) {
                            Toast.makeText(this, "Cadastro efetuado!", Toast.LENGTH_SHORT)
                                .show()
                            startActivity(intent)
                        } else {
                            Toast.makeText(
                                this,
                                Task.exception.toString(), Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            } else {
                Toast.makeText(
                    this,
                    "As senhas não são iguais!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            Toast.makeText(
                this,
                "Prencha corretamente todos os campos!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}


