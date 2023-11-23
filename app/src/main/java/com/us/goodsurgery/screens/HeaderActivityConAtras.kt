package com.us.goodsurgery.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.us.goodsurgery.R

class HeaderActivityConAtras : AppCompatActivity() {

    private lateinit var btnVolverAtras:ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_header_con_atras)

    }
}