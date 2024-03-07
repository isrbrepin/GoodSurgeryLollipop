package com.us.goodsurgery.screens.patologias.cirugiaabdominal.polipos.recto.postoperatorio

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
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.polipos.colon.postoperatorio.DietaInviernoColonActivity
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.polipos.colon.postoperatorio.DietaVeranoColonActivity

class DietasRectoActivity : AppCompatActivity() {

    private lateinit var btnDietaVerano: Button
    private lateinit var btnDietaInvierno: Button
    private lateinit var btnVolverAtras: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dietas_recto)
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

        // Lógica de navegación

        btnDietaVerano = findViewById(R.id.btn_dieta_verano)
        btnDietaVerano.setOnClickListener{
            intent = Intent(this, DietaVeranoRectoActivity::class.java)
            startActivity(intent)
        }

        btnDietaInvierno = findViewById(R.id.btn_dieta_invierno)
        btnDietaInvierno.setOnClickListener{
            intent = Intent(this, DietaInviernoRectoActivity::class.java)
            startActivity(intent)
        }
    }
}