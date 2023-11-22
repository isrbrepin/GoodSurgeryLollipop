package com.us.goodsurgery.screens.patologias.cirugiaabdominal.reconstruccion.ileostomia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.us.goodsurgery.R
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.reconstruccion.colostomia.InfoColostomiaActivity
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.reconstruccion.colostomia.postoperatorio.PostoperatorioColostomiaActivity
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.reconstruccion.colostomia.preoperatorio.PreoperatorioColostomiaActivity
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.reconstruccion.ileostomia.postoperatorio.PostoperatorioIleostomaActivity
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.reconstruccion.ileostomia.preoperatorio.PreoperatorioIleostomaActivity

class IleostomiaActivity : AppCompatActivity() {

    private lateinit var btnInformacion:Button
    private lateinit var btnPreoperatorio:Button
    private lateinit var btnPostoperatorio:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ileostomia)

        btnInformacion = findViewById(R.id.btn_informacion_del_proceso)
        btnInformacion.setOnClickListener{
            intent = Intent(this, InfoIleostomiaActivity::class.java)
            startActivity(intent)
        }

        btnPreoperatorio = findViewById(R.id.btn_preoperatorio)
        btnPreoperatorio.setOnClickListener{
            intent = Intent(this, PreoperatorioIleostomaActivity::class.java)
            startActivity(intent)
        }
        btnPostoperatorio = findViewById(R.id.btn_postoperatorio)
        btnPostoperatorio.setOnClickListener{
            intent = Intent(this, PostoperatorioIleostomaActivity::class.java)
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

    fun openInfoActivity(view: View) {
        val intent = Intent(this, InfoIleostomiaActivity::class.java)
        startActivity(intent)
    }
    fun openPreoperatorioIleostomaActivity(view: View) {
        val intent = Intent(this, PreoperatorioIleostomaActivity::class.java)
        startActivity(intent)
    }
    fun openPostoperatorioIleostomaActivity(view: View) {
        val intent = Intent(this, PostoperatorioIleostomaActivity::class.java)
        startActivity(intent)
    }
}