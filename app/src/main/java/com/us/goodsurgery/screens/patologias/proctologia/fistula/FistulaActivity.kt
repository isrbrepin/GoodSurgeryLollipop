package com.us.goodsurgery.screens.patologias.proctologia.fistula

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.us.goodsurgery.R
import com.us.goodsurgery.screens.patologias.proctologia.fistula.postoperatorio.PostoperatorioFistulaActivity
import com.us.goodsurgery.screens.patologias.proctologia.fistula.preoperatorio.PreoperatorioFistulaActivity

class FistulaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fistula)

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
        val intent = Intent(this, InfoFistulaActivity::class.java)
        startActivity(intent)
    }
    fun openPreoperatorioFistulaActivity(view: View) {
        val intent = Intent(this, PreoperatorioFistulaActivity::class.java)
        startActivity(intent)
    }
    fun openPostoperatorioFistulaActivity(view: View) {
        val intent = Intent(this, PostoperatorioFistulaActivity::class.java)
        startActivity(intent)
    }
}