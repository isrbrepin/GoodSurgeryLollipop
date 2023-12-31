package com.us.goodsurgery.screens.patologias.cirugiaabdominal.polipos

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
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.us.goodsurgery.R
import com.us.goodsurgery.screens.PatologiaActivity
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.AbdomenActivity
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.inflamatoria.estoma.EstomaActivity
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.inflamatoria.reseccion.ReseccionActivity
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.polipos.colon.ColonActivity
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.polipos.recto.RectoActivity

class CancerActivity : AppCompatActivity() {

    private lateinit var btnColon:Button
    private lateinit var btnRecto:Button
    private lateinit var btnVolverAtras: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cancer)

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


        //Lógica de navegación

        btnColon = findViewById(R.id.btn_polipo_colon)
        btnColon.setOnClickListener{
            intent = Intent(this, ColonActivity::class.java)
            startActivity(intent)
        }

        btnRecto = findViewById(R.id.btn_polipo_recto)
        btnRecto.setOnClickListener{
            intent = Intent(this, RectoActivity::class.java)
            startActivity(intent)
        }


        // Lógica de los subtitulos

        val textInfo = findViewById<TextView>(R.id.text_info)
        val fullText = getString(R.string.polipos)

        // Crear el texto con diferentes partes clicables
        val spannableString = SpannableString(fullText)

        // Definir los clics y sus comportamientos
        val patologiaClick = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = Intent(this@CancerActivity, PatologiaActivity::class.java)
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
                val intent = Intent(this@CancerActivity, AbdomenActivity::class.java)
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
}