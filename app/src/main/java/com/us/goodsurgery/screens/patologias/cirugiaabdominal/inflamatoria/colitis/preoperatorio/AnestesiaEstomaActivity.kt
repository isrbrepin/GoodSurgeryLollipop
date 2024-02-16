package com.us.goodsurgery.screens.patologias.cirugiaabdominal.inflamatoria.colitis.preoperatorio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.core.content.FileProvider
import com.us.goodsurgery.BuildConfig
import com.us.goodsurgery.R
import com.us.goodsurgery.screens.PrincipalActivity
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

class AnestesiaEstomaActivity : AppCompatActivity() {

    private lateinit var btnVolverAtras: ImageButton
    private lateinit var btnPdf: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anestesia_estoma)

        //Logica pdf

        btnPdf = findViewById(R.id.btn_pdf)

        btnPdf.setOnClickListener(View.OnClickListener {
            // Nombre del PDF en la carpeta assets
            val assetFileName = "Instrucciones EPA general.pdf"
            // Ruta de destino en el almacenamiento interno
            val destinationPath =
                getExternalFilesDir(null).toString() + File.separator + assetFileName
            try {
                // Copiar el archivo desde assets al almacenamiento interno
                val `in` = assets.open(assetFileName)
                val out: OutputStream = FileOutputStream(destinationPath)
                val buffer = ByteArray(1024)
                var read: Int
                while (`in`.read(buffer).also { read = it } != -1) {
                    out.write(buffer, 0, read)
                }
                out.flush()
                out.close()
                `in`.close()

                // Abrir el PDF descargado
                val file = File(destinationPath)
                val uri = FileProvider.getUriForFile(
                    this,
                    BuildConfig.APPLICATION_ID + ".provider", file
                )
                val intent = Intent(Intent.ACTION_VIEW)
                intent.setDataAndType(uri, "application/pdf")
                intent.flags =
                    Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_GRANT_READ_URI_PERMISSION
                startActivity(intent)
            } catch (e: IOException) {
                e.printStackTrace()
                // Manejar el error
            }
        })

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
    }
}