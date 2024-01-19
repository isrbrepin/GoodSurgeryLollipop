package com.us.goodsurgery.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import com.us.goodsurgery.R
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.AbdomenActivity
import com.us.goodsurgery.screens.patologias.proctologia.ProctologiaActivity
import com.us.goodsurgery.screens.patologias.suelopelvico.SueloActivity

class PatologiaActivity : AppCompatActivity() {

    private lateinit var btnCirugiaAbdominal:Button
    private lateinit var btnProctologia:Button
    private lateinit var btnSueloPelvico:Button
    private lateinit var btnVolverAtras:ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patologia)

        // Lógica de la Header

        btnVolverAtras = findViewById(R.id.btn_back)
        btnVolverAtras.setOnClickListener {
            // Manejar el clic de la flecha para ir a la pantalla anterior
            onBackPressedDispatcher.onBackPressed()
            // O puedes usar la función finish() para cerrar la actividad si es lo que necesitas
            // finish()
        }

        val btnOpenOverlay: Button = findViewById(R.id.btn_open_overlay)

        btnOpenOverlay.setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.custom_dialog, null)
            val builder = AlertDialog.Builder(this, R.style.CustomAlertDialog)
            builder.setView(dialogView)

            val dialog = builder.create()

            val layoutParams = WindowManager.LayoutParams().apply {
                copyFrom(dialog.window?.attributes)
                gravity = Gravity.CENTER // Cambiar a la posición que desees
            }
            dialog.window?.attributes = layoutParams

            val dismissButton: Button = dialogView.findViewById(R.id.dismiss_button)

            dismissButton.setOnClickListener {
                // Cierra el AlertDialog
                dialog.dismiss()
            }

            dialog.show()
        }

        // Lógica de los botones

        btnCirugiaAbdominal = findViewById(R.id.btn_cirugia_abdominal)
        btnCirugiaAbdominal.setOnClickListener{
            intent = Intent(this, AbdomenActivity::class.java)
            startActivity(intent)
        }

        btnProctologia = findViewById(R.id.btn_proctologia)
        btnProctologia.setOnClickListener{
            intent = Intent(this, ProctologiaActivity::class.java)
            startActivity(intent)
        }

        btnSueloPelvico = findViewById(R.id.btn_suelo_pelvico)
        btnSueloPelvico.setOnClickListener{
            intent = Intent(this, SueloActivity::class.java)
            startActivity(intent)
        }

        val btnHome: ImageButton = findViewById(R.id.btn_home)
        btnHome.setOnClickListener{
            intent = Intent(this, PrincipalActivity::class.java)
            startActivity(intent)
        }
    }
}