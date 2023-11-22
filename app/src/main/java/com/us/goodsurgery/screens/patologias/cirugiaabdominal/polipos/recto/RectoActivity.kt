package com.us.goodsurgery.screens.patologias.cirugiaabdominal.polipos.recto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.us.goodsurgery.R
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.polipos.recto.postoperatorio.PostoperatorioRectoActivity
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.polipos.recto.preoperatorio.PreoperatorioRectoActivity

class RectoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recto)

        val btnOpenOverlay: Button = findViewById(R.id.btn_open_overlay)

        btnOpenOverlay.setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.custom_dialog, null)
            val builder = AlertDialog.Builder(this, R.style.CustomAlertDialog)
            builder.setView(dialogView)

            val dialog = builder.create()

            val layoutParams = WindowManager.LayoutParams().apply {
                copyFrom(dialog.window?.attributes)
                gravity = Gravity.TOP // Cambiar a la posición que desees
                y = 60.dpToPx()
            }
            dialog.window?.attributes = layoutParams

            dialog.show()

        }

    }

    private fun Int.dpToPx(): Int {
        val scale = resources.displayMetrics.density
        return (this * scale + 0.5f).toInt()
    }

    fun openInfoActivity(view: View) {
        val intent = Intent(this, InfoRectoActivity::class.java)
        startActivity(intent)
    }
    fun openPreoperatorioRectoActivity(view: View) {
        val intent = Intent(this, PreoperatorioRectoActivity::class.java)
        startActivity(intent)
    }
    fun openPostoperatorioRectoActivity(view: View) {
        val intent = Intent(this, PostoperatorioRectoActivity::class.java)
        startActivity(intent)
    }
}