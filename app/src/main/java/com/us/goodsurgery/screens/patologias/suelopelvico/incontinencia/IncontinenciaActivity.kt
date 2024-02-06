package com.us.goodsurgery.screens.patologias.suelopelvico.incontinencia

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
import com.us.goodsurgery.screens.PrincipalActivity
import com.us.goodsurgery.screens.patologias.proctologia.hemorroides.InfoHemorroidesActivity
import com.us.goodsurgery.screens.patologias.proctologia.hemorroides.postoperatorio.PostoperatorioHemorroidesActivity
import com.us.goodsurgery.screens.patologias.proctologia.hemorroides.preoperatorio.PreoperatorioHemorroidesActivity
import com.us.goodsurgery.screens.patologias.suelopelvico.incontinencia.postoperatorio.PostoperatorioIncontinenciaActivity
import com.us.goodsurgery.screens.patologias.suelopelvico.incontinencia.preoperatorio.PreoperatorioIncontinenciaActivity

class IncontinenciaActivity : AppCompatActivity() {

    private lateinit var btnInformacion:Button
    private lateinit var btnPreoperatorio:Button
    private lateinit var btnPostoperatorio:Button
    private lateinit var btnVolverAtras: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incontinencia)

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

        btnInformacion = findViewById(R.id.btn_informacion_del_proceso)
        btnInformacion.setOnClickListener{
            intent = Intent(this, InfoIncontinenciaActivity::class.java)
            startActivity(intent)
        }

        btnPreoperatorio = findViewById(R.id.btn_preoperatorio)
        btnPreoperatorio.setOnClickListener{
            intent = Intent(this, PreoperatorioIncontinenciaActivity::class.java)
            startActivity(intent)
        }
        btnPostoperatorio = findViewById(R.id.btn_postoperatorio)
        btnPostoperatorio.setOnClickListener{
            intent = Intent(this, PostoperatorioIncontinenciaActivity::class.java)
            startActivity(intent)
        }
    }
}