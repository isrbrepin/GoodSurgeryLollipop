package com.us.goodsurgery.screens.patologias.cirugiaabdominal.reconstruccion.colostomia.preoperatorio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.FileProvider
import com.us.goodsurgery.BuildConfig
import com.us.goodsurgery.R
import com.us.goodsurgery.screens.PrincipalActivity
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

class PreparacionColostomiaActivity : AppCompatActivity() {

    private lateinit var btnVolverAtras: ImageButton
    private lateinit var info_preo_recons1: TextView
    private lateinit var info_preo_recons2: TextView
    private lateinit var info_preo_recons3: TextView
    private lateinit var info_preo_recons4: TextView
    private lateinit var info_preo_recons5: TextView
    private lateinit var btnPdf: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preparacion_colostomia)

        //Logica pdf

        btnPdf = findViewById(R.id.btn_pdf)

        btnPdf.setOnClickListener(View.OnClickListener {
            // Nombre del PDF en la carpeta assets
            val assetFileName = "Indicaciones preOP Reconstruccion del transito.pdf"
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
        //------------------------------------------------------------------------------------------

        info_preo_recons1 = findViewById(R.id.text_texto_preo_recons)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            info_preo_recons1.setText(Html.fromHtml(getString(R.string.text_texto_preo_recons), Html.FROM_HTML_MODE_LEGACY));
        } else {
            info_preo_recons1.setText(Html.fromHtml(getString(R.string.text_texto_preo_recons)));
        }

        info_preo_recons2 = findViewById(R.id.text_texto_preo_recons_lista1)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            info_preo_recons2.setText(Html.fromHtml(getString(R.string.text_texto_preo_recos_lista1), Html.FROM_HTML_MODE_LEGACY));
        } else {
            info_preo_recons2.setText(Html.fromHtml(getString(R.string.text_texto_preo_recos_lista1)));
        }

        info_preo_recons3 = findViewById(R.id.text_texto_preo_recons_lista2)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            info_preo_recons3.setText(Html.fromHtml(getString(R.string.text_texto_preo_recons_lista2), Html.FROM_HTML_MODE_LEGACY));
        } else {
            info_preo_recons3.setText(Html.fromHtml(getString(R.string.text_texto_preo_recons_lista2)));
        }

        info_preo_recons4 = findViewById(R.id.text_texto_preo_recons_lista3)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            info_preo_recons4.setText(Html.fromHtml(getString(R.string.text_texto_preo_recons_lista3), Html.FROM_HTML_MODE_LEGACY));
        } else {
            info_preo_recons4.setText(Html.fromHtml(getString(R.string.text_texto_preo_recons_lista3)));
        }

        info_preo_recons5 = findViewById(R.id.text_texto_preo_recons_lista4)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            info_preo_recons5.setText(Html.fromHtml(getString(R.string.text_texto_preo_recons_lista4), Html.FROM_HTML_MODE_LEGACY));
        } else {
            info_preo_recons5.setText(Html.fromHtml(getString(R.string.text_texto_preo_recons_lista4)));
        }

        //------------------------------------------------------------------------------------------

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