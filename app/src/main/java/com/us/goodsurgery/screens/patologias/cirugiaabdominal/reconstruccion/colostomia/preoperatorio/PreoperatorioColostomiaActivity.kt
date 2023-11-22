package com.us.goodsurgery.screens.patologias.cirugiaabdominal.reconstruccion.colostomia.preoperatorio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.us.goodsurgery.R
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.polipos.recto.preoperatorio.AnestesiaRectoActivity
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.polipos.recto.preoperatorio.HospitalRectoActivity
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.polipos.recto.preoperatorio.IngresoRectoActivity
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.polipos.recto.preoperatorio.PreparacionRectoActivity

class PreoperatorioColostomiaActivity : AppCompatActivity() {

    private lateinit var btnAnestesia:Button
    private lateinit var btnHospital:Button
    private lateinit var btnIngreso:Button
    private lateinit var btnPreparacion:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preoperatorio_colostomia)

        btnHospital = findViewById(R.id.btn_hospital)
        btnHospital.setOnClickListener{
            intent = Intent(this, HospitalColostomiaActivity::class.java)
            startActivity(intent)
        }

        btnAnestesia = findViewById(R.id.btn_anestesia)
        btnAnestesia.setOnClickListener{
            intent = Intent(this, AnestesiaColostomiaActivity::class.java)
            startActivity(intent)
        }

        btnIngreso = findViewById(R.id.btn_ingreso)
        btnIngreso.setOnClickListener{
            intent = Intent(this, IngresoColostomiaActivity::class.java)
            startActivity(intent)
        }

        btnPreparacion = findViewById(R.id.btn_preparacion)
        btnPreparacion.setOnClickListener{
            intent = Intent(this, PreparacionColostomiaActivity::class.java)
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

    fun openAnestesiaActivity(view: View) {
        val intent = Intent(this, AnestesiaColostomiaActivity::class.java)
        startActivity(intent)
    }
    fun openIngresoActivity(view: View) {
        val intent = Intent(this, IngresoColostomiaActivity::class.java)
        startActivity(intent)
    }
    fun openPreparacionActivity(view: View) {
        val intent = Intent(this, PreparacionColostomiaActivity::class.java)
        startActivity(intent)
    }
    fun openHospitalActivity(view: View) {
        val intent = Intent(this, HospitalColostomiaActivity::class.java)
        startActivity(intent)
    }
}