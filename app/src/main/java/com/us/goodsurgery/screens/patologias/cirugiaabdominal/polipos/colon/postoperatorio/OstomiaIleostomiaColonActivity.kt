package com.us.goodsurgery.screens.patologias.cirugiaabdominal.polipos.colon.postoperatorio

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.text.Html
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.us.goodsurgery.BuildConfig
import com.us.goodsurgery.R
import com.us.goodsurgery.screens.PrincipalActivity
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.inflamatoria.colitis.InfoEstomaActivity
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class OstomiaIleostomiaColonActivity : AppCompatActivity() {

    private lateinit var btnVolverAtras: ImageButton
    private lateinit var btnPdf: Button
    private lateinit var ostomia1: TextView
    private lateinit var ostomia2: TextView
    private lateinit var ostomia3: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ostomia_ileostomia_colon)

        //Logica pdf

        btnPdf = findViewById(R.id.btn_pdf)

        btnPdf.setOnClickListener {
            // Verificar la versión de Android del dispositivo
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q && ContextCompat.checkSelfPermission(
                    this, Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // Para Android 9 (Pie) o inferior, solicitar permiso de almacenamiento
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    InfoEstomaActivity.MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE
                )
            } else {
                // Para Android 10 (Q) o superior, no es necesario el permiso para escribir en el almacenamiento específico de la app
                downloadPdf()
            }
        }

        //------------------------------------------------------------------------------------------

        // Lógica para permitir que se use el texto en html para poner negritas y rayadas por ejemplo

        ostomia1 = findViewById(R.id.text_ostomia_ileo1)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            ostomia1.setText(
                Html.fromHtml(
                    getString(R.string.text_ostomia_ileo1),
                    Html.FROM_HTML_MODE_LEGACY
                )
            );
        } else {
            ostomia1.setText(Html.fromHtml(getString(R.string.text_ostomia_ileo1)));
        }

        ostomia2 = findViewById(R.id.text_ostomia_ileo2)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            ostomia2.setText(
                Html.fromHtml(
                    getString(R.string.text_ostomia_ileo2),
                    Html.FROM_HTML_MODE_LEGACY
                )
            );
        } else {
            ostomia2.setText(Html.fromHtml(getString(R.string.text_ostomia_ileo2)));
        }

        ostomia3 = findViewById(R.id.text_ostomia_ileo3)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            ostomia3.setText(
                Html.fromHtml(
                    getString(R.string.text_ostomia_ileo3),
                    Html.FROM_HTML_MODE_LEGACY
                )
            );
        } else {
            ostomia3.setText(Html.fromHtml(getString(R.string.text_ostomia_ileo3)));
        }

        // Lógica de la Header

        val btnHome: ImageButton = findViewById(R.id.btn_home)
        btnHome.setOnClickListener {
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

    private fun downloadPdf() {
        val assetFileName = "Indicaciones para pacientes con ILEOSTOMIA.pdf"
        try {
            // Abre el archivo desde los assets
            assets.open(assetFileName).use { inputStream ->
                // Define el archivo de destino en el almacenamiento específico de la aplicación
                val file = File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), assetFileName)

                // Escribe el contenido del archivo de assets al archivo de destino
                FileOutputStream(file).use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }

            // Crear un Intent para abrir el archivo con una aplicación de PDF
            val fileUri = FileProvider.getUriForFile(
                this,
                "${BuildConfig.APPLICATION_ID}.provider",
                File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), assetFileName)
            )

            val openIntent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(fileUri, "application/pdf")
                flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            }
            startActivity(openIntent)
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Error al descargar el archivo: ${e.message}", Toast.LENGTH_LONG)
                .show()
        }
    }
}