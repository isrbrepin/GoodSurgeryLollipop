package com.us.goodsurgery.screens.patologias.cirugiaabdominal.inflamatoria.colitis

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.pdf.PdfRenderer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.ParcelFileDescriptor
import android.text.Html
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.viewpager2.widget.ViewPager2
import com.us.goodsurgery.BuildConfig
import com.us.goodsurgery.R
import com.us.goodsurgery.Utils.PdfPageAdapter
import com.us.goodsurgery.screens.PrincipalActivity
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

class InfoEstomaActivity : AppCompatActivity() {

    private lateinit var btnVolverAtras: ImageButton
    private lateinit var btnPdf: Button
    private lateinit var txt_colitis1: TextView
    private lateinit var txt_colitis2: TextView

    companion object {
        const val MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_estoma)

        //Logica pdf

        //Inicialización de componentes UI
        initializeUI()

        btnPdf = findViewById(R.id.btn_pdf)

        btnPdf.setOnClickListener(View.OnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE)
            } else {
                downloadPdf()
                // Nombre del PDF en la carpeta assets
                val assetFileName = "COLITIS  ULCEROSA.pdf"
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
            }

        })


        //------------------------------------------------------------------------------------------

// Lógica para permitir que se use el texto en html para poner negritas y rayadas por ejemplo

        txt_colitis1 = findViewById(R.id.text_colitis1)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            txt_colitis1.setText(Html.fromHtml(getString(R.string.text_colitis1), Html.FROM_HTML_MODE_LEGACY));
        } else {
            txt_colitis1.setText(Html.fromHtml(getString(R.string.text_colitis1)));
        }

        txt_colitis2 = findViewById(R.id.text_colitis2)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            txt_colitis2.setText(Html.fromHtml(getString(R.string.text_colitis2), Html.FROM_HTML_MODE_LEGACY));
        } else {
            txt_colitis2.setText(Html.fromHtml(getString(R.string.text_colitis2)));
        }
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
    private fun initializeUI() {
        btnPdf = findViewById(R.id.btn_pdf)
        txt_colitis1 = findViewById(R.id.text_colitis1)
        txt_colitis2 = findViewById(R.id.text_colitis2)
        btnVolverAtras = findViewById(R.id.btn_back)
        // Aquí puedes agregar más inicializaciones si es necesario
    }

    private fun downloadPdf() {
        val assetFileName = "COLITIS  ULCEROSA.pdf"
        try {
            val inputStream = assets.open(assetFileName)
            val downloadFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

            var file = File(downloadFolder, assetFileName)
            var fileName = assetFileName

            // Verifica si el archivo ya existe y ajusta el nombre consecuentemente
            var fileNumber = 0
            while (file.exists()) {
                fileNumber++
                val fileNameWithoutExtension = assetFileName.substringBeforeLast(".")
                val fileExtension = assetFileName.substringAfterLast(".", "")
                fileName = "$fileNameWithoutExtension ($fileNumber).$fileExtension"
                file = File(downloadFolder, fileName)
            }

            val outputStream: FileOutputStream = FileOutputStream(file)
            inputStream.copyTo(outputStream)
            outputStream.flush()
            outputStream.close()
            inputStream.close()

            Toast.makeText(this, "Se ha descargado $fileName.", Toast.LENGTH_LONG).show()
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Error al descargar el archivo: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                downloadPdf()
            } else {
                Toast.makeText(this, "Permiso de almacenamiento denegado", Toast.LENGTH_SHORT).show()
            }
        }
    }
}