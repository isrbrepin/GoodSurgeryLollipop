package com.us.goodsurgery.screens.patologias.cirugiaabdominal.reconstruccion

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
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.us.goodsurgery.R
import com.us.goodsurgery.screens.PatologiaActivity
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.AbdomenActivity
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.polipos.colon.ColonActivity
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.polipos.recto.RectoActivity
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.reconstruccion.colostomia.ColostomiaActivity
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.reconstruccion.ileostomia.IleostomiaActivity

class TransitoActivity : AppCompatActivity() {

    private lateinit var btnColostomia:Button
    private lateinit var btnIleostomia:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transito)

        //Lógica del boton
        btnColostomia = findViewById(R.id.btn_colostomia_terminal)
        btnColostomia.setOnClickListener{
            intent = Intent(this, ColostomiaActivity::class.java)
            startActivity(intent)
        }

        btnIleostomia = findViewById(R.id.btn_ileostomia_lateral)
        btnIleostomia.setOnClickListener{
            intent = Intent(this, IleostomiaActivity::class.java)
            startActivity(intent)
        }

        // Lógica del header
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


        val textInfo = findViewById<TextView>(R.id.text_info)
        val fullText = getString(R.string.reconstrucci_n_del_tr_nsito)

        // Crear el texto con diferentes partes clicables
        val spannableString = SpannableString(fullText)

        // Definir los clics y sus comportamientos
        val patologiaClick = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = Intent(this@TransitoActivity, PatologiaActivity::class.java)
                startActivity(intent)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
                ds.color = Color.parseColor("#FFFFFF")
            }
        }

        val cirugiaClick = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = Intent(this@TransitoActivity, AbdomenActivity::class.java)
                startActivity(intent)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
                ds.color = Color.parseColor("#FFFFFF")
            }
        }


        // Asignar los clics a las partes correspondientes del texto
        spannableString.setSpan(patologiaClick, 0, 18, 0)
        spannableString.setSpan(cirugiaClick, 21, 38, 0)

        // Asignar el SpannableString al TextView
        textInfo.text = spannableString
        textInfo.movementMethod = LinkMovementMethod.getInstance()

    }

    fun openColostomiaActivity(view: View) {
        val intent = Intent(this, ColostomiaActivity::class.java)
        startActivity(intent)
        showToast("Patología escogida correctamente")
    }
    fun openIleostomiaActivity(view: View) {
        val intent = Intent(this, IleostomiaActivity::class.java)
        startActivity(intent)
        showToast("Patología escogida correctamente")
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}