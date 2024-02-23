package com.us.goodsurgery.screens.patologias.cirugiaabdominal.inflamatoria

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.us.goodsurgery.R
import com.us.goodsurgery.screens.PatologiaActivity
import com.us.goodsurgery.screens.PrincipalActivity
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.AbdomenActivity
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.inflamatoria.colitis.EstomaActivity
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.inflamatoria.crohn.ReseccionActivity

class InflamatoriaActivity : AppCompatActivity() {

    private lateinit var btnEstoma:Button
    private lateinit var btnReseccion:Button
    private lateinit var btnVolverAtras: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inflamatoria)

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


        // Lógica de navegación

        btnEstoma = findViewById(R.id.btn_creacion_de_estoma)
        btnEstoma.setOnClickListener {
            intent = Intent(this, EstomaActivity::class.java)
            startActivity(intent)
        }

        btnReseccion = findViewById(R.id.btn_reseccion_intestinal)
        btnReseccion.setOnClickListener {
            intent = Intent(this, ReseccionActivity::class.java)
            startActivity(intent)
        }

        val btnHome: ImageButton = findViewById(R.id.btn_home)
        btnHome.setOnClickListener {
            intent = Intent(this, PrincipalActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}