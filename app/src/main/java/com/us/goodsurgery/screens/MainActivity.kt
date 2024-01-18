package com.us.goodsurgery.screens

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.us.goodsurgery.R
import java.util.Timer
import java.util.TimerTask

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set the status bar color
        window.statusBarColor = Color.parseColor("#11111F")

        // Set the navigation bar color
        window.navigationBarColor = Color.parseColor("#11111F")

            val tarea = object : TimerTask() {
                override fun run() {
                    val intent = Intent(this@MainActivity, PrincipalActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }

            val tiempo = Timer()
            tiempo.schedule(tarea, 2000L) // Cambia el valor 500L a la cantidad de milisegundos que deseas para el retraso
        }
}
