package com.us.goodsurgery.screens.patologias.cirugiaabdominal.inflamatoria.estoma.postoperatorio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.us.goodsurgery.R
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.inflamatoria.estoma.InfoEstomaActivity
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.inflamatoria.estoma.preoperatorio.PreoperatorioEstomaActivity

class PostoperatorioEstomaActivity : AppCompatActivity() {

    private lateinit var btnAlta:Button
    private lateinit var btnOstomia:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_postoperatorio_estoma)

        btnAlta = findViewById(R.id.btn_al_alta)
        btnAlta.setOnClickListener{
            intent = Intent(this, AltaEstomaActivity::class.java)
            startActivity(intent)
        }

        btnOstomia = findViewById(R.id.btn_ostomia)
        btnOstomia.setOnClickListener{
            intent = Intent(this, OstomiaEstomaActivity::class.java)
            startActivity(intent)
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
    }

    fun openAltaActivity(view: View) {
        val intent = Intent(this, AltaEstomaActivity::class.java)
        startActivity(intent)
    }
    fun openOstomiaActivity(view: View) {
        val intent = Intent(this, OstomiaEstomaActivity::class.java)
        startActivity(intent)
    }
}