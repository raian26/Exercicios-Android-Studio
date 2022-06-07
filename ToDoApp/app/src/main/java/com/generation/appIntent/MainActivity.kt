package com.generation.appIntent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.generation.apptodo.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("Cicle", "onCreate")
        val buttonNext = findViewById<Button>(R.id.buttonNext)
        val intent2 = Intent(this, SecondActivity::class.java)
        buttonNext.setOnClickListener {
            startActivity(intent2)

        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("Cicle", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Cicle", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Cicle", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Cicle", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Cicle", "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Cicle", "onRestart")
    }
}