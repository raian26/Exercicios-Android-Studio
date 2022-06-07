package com.generation.appIntent


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.generation.apptodo.R

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val buttonVoltar = findViewById<Button>(R.id.buttonVoltar)
        buttonVoltar.setOnClickListener {
            finish()
        }
    }
}