package com.us.goodsurgery.screens.patologias.cirugiaabdominal.polipos.colon.postoperatorio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import com.us.goodsurgery.R
import com.us.goodsurgery.screens.PrincipalActivity
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.reconstruccion.ileostomia.postoperatorio.DietasActivity

class PostoperatorioColonActivity : AppCompatActivity() {

    private lateinit var btnAlta:Button
    private lateinit var btnOstomia:Button
    private lateinit var btnVolverAtras: ImageButton
    private lateinit var btnDietasAlta:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_postoperatorio_colon)

        // Lógica de la Header

        val btnHome: ImageButton = findViewById(R.id.btn_home)
        btnHome.setOnClickListener{
            intent = Intent(this, PrincipalActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

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


        // Lógica de la navegación

        btnAlta = findViewById(R.id.btn_al_alta)
        btnAlta.setOnClickListener{
            intent = Intent(this, AltaColonActivity::class.java)
            startActivity(intent)
        }

        btnOstomia = findViewById(R.id.btn_ostomia)
        btnOstomia.setOnClickListener{
            intent = Intent(this, OstomiaColonActivity::class.java)
            startActivity(intent)
        }

        btnDietasAlta = findViewById(R.id.btn_dietas_al_alta)
        btnDietasAlta.setOnClickListener{
            intent = Intent(this, DietasColonActivity::class.java)
            startActivity(intent)
        }
    }
}