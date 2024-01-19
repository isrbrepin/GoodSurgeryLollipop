package com.us.goodsurgery.screens.patologias.cirugiaabdominal

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
import androidx.appcompat.app.AlertDialog
import com.us.goodsurgery.R
import com.us.goodsurgery.screens.PatologiaActivity
import com.us.goodsurgery.screens.PrincipalActivity
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.inflamatoria.InflamatoriaActivity
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.polipos.CancerActivity
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.reconstruccion.TransitoActivity

class AbdomenActivity : AppCompatActivity() {

    private lateinit var btnInflamatoria:Button
    private lateinit var btnPolipos:Button
    private lateinit var btnReconstruccion:Button
    private lateinit var btnVolverAtras: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_abdomen)
        val btnOpenOverlay: Button = findViewById(R.id.btn_open_overlay)

        // Lógica de la Header

        btnVolverAtras = findViewById(R.id.btn_back)
        btnVolverAtras.setOnClickListener {
            // Manejar el clic de la flecha para ir a la pantalla anterior
            onBackPressedDispatcher.onBackPressed()
            // O puedes usar la función finish() para cerrar la actividad si es lo que necesitas
            // finish()
        }


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

        btnInflamatoria = findViewById(R.id.btn_inflamatoria)
        btnInflamatoria.setOnClickListener{
            intent = Intent(this, InflamatoriaActivity::class.java)
            startActivity(intent)
        }

        btnPolipos = findViewById(R.id.btn_polipos_colorrectales)
        btnPolipos.setOnClickListener{
            intent = Intent(this, CancerActivity::class.java)
            startActivity(intent)
        }

        btnReconstruccion = findViewById(R.id.btn_reconstruccion_del_transito)
        btnReconstruccion.setOnClickListener{
            intent = Intent(this, TransitoActivity::class.java)
            startActivity(intent)
        }

        val btnHome: ImageButton = findViewById(R.id.btn_home)
        btnHome.setOnClickListener{
            intent = Intent(this, PrincipalActivity::class.java)
            startActivity(intent)
        }


        // Lógica de los subtitulos

        val textInfo = findViewById<TextView>(R.id.text_abdomen)
        val fullText = getString(R.string.text_abdominal)

        // Crear el texto con diferentes partes clicables
        val spannableString = SpannableString(fullText)

        // Definir los clics y sus comportamientos
        val patologiaClick = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = Intent(this@AbdomenActivity, PatologiaActivity::class.java)
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

        // Asignar el SpannableString al TextView
        textInfo.text = spannableString
        textInfo.movementMethod = LinkMovementMethod.getInstance()

    }
}