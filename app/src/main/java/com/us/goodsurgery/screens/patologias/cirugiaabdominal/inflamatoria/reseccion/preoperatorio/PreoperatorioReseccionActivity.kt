package com.us.goodsurgery.screens.patologias.cirugiaabdominal.inflamatoria.reseccion.preoperatorio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import com.us.goodsurgery.R
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.inflamatoria.estoma.preoperatorio.AnestesiaEstomaActivity
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.inflamatoria.estoma.preoperatorio.HospitalEstomaActivity
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.inflamatoria.estoma.preoperatorio.IngresoEstomaActivity
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.inflamatoria.estoma.preoperatorio.PreparacionEstomaActivity

class PreoperatorioReseccionActivity : AppCompatActivity() {

    private lateinit var btnAnestesia:Button
    private lateinit var btnHospital:Button
    private lateinit var btnIngreso:Button
    private lateinit var btnPreparacion:Button
    private lateinit var btnVolverAtras: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preoperatorio_reseccion)

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

        btnHospital = findViewById(R.id.btn_hospital)
        btnHospital.setOnClickListener{
            intent = Intent(this, HospitalReseccionActivity::class.java)
            startActivity(intent)
        }

        btnAnestesia = findViewById(R.id.btn_anestesia)
        btnAnestesia.setOnClickListener{
            intent = Intent(this, AnestesiaReseccionActivity::class.java)
            startActivity(intent)
        }

        btnIngreso = findViewById(R.id.btn_ingreso)
        btnIngreso.setOnClickListener{
            intent = Intent(this, IngresoReseccionActivity::class.java)
            startActivity(intent)
        }

        btnPreparacion = findViewById(R.id.btn_preparacion)
        btnPreparacion.setOnClickListener{
            intent = Intent(this, PreparacionReseccionActivity::class.java)
            startActivity(intent)
        }
    }
}