package com.us.goodsurgery.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.us.goodsurgery.R
import java.util.Timer
import java.util.TimerTask

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
