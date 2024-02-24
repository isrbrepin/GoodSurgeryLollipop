package com.us.goodsurgery.screens.patologias.cirugiaabdominal.reconstruccion.ileostomia.postoperatorio

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.ParcelFileDescriptor
import android.text.Html
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.us.goodsurgery.BuildConfig
import com.us.goodsurgery.R
import com.us.goodsurgery.Utils.PdfPageAdapter
import com.us.goodsurgery.screens.PrincipalActivity
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.inflamatoria.colitis.InfoEstomaActivity
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

class DietasAltaIleostomiaActivity : AppCompatActivity() {

    private lateinit var btnVolverAtras: ImageButton
    private lateinit var alta_reconstruccion: TextView
    private lateinit var pdfPageImageView: ImageView
    private lateinit var btnPdf: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dietas_alta_ileostomia)

        //Logica pdf

        btnPdf = findViewById(R.id.btn_pdf)

        btnPdf.setOnClickListener(View.OnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    InfoEstomaActivity.MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE
                )
            } else {
                downloadPdf()
            }

        })

        showPdfPage()

        setupViewPager(findViewById(R.id.pdfViewPager))

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

    private fun setupViewPager(viewPager: ViewPager2) {
        val leftArrow: ImageView = findViewById(R.id.left_arrow)
        val rightArrow: ImageView = findViewById(R.id.right_arrow)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                // Mostrar la flecha izquierda si no estamos en la primera página
                leftArrow.visibility = if (position > 0) View.VISIBLE else View.INVISIBLE

                // Mostrar la flecha derecha si no estamos en la última página
                rightArrow.visibility = if (position < viewPager.adapter!!.itemCount - 1) View.VISIBLE else View.INVISIBLE

                leftArrow.setOnClickListener {
                    val currentItem = viewPager.currentItem
                    if (currentItem > 0) {
                        viewPager.currentItem = currentItem - 1
                    }
                }

                rightArrow.setOnClickListener {
                    val currentItem = viewPager.currentItem
                    if (currentItem < viewPager.adapter!!.itemCount - 1) {
                        viewPager.currentItem = currentItem + 1
                    }
                }
            }
        })

        // Inicialmente, solo mostramos la flecha derecha si hay más de una página
        if (viewPager.adapter?.itemCount ?: 0 > 1) {
            rightArrow.visibility = View.VISIBLE
        }
    }

    private fun showPdfPage() {
        val assetFileName = "Dietas al alta Ileostomía - Menú de VERANO.pdf"
        val destinationPath = getExternalFilesDir(null).toString() + File.separator + assetFileName
        copyFileFromAssets(assetFileName, destinationPath)

        try {
            val file = File(destinationPath)
            val fileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)
            val pdfRenderer = PdfRenderer(fileDescriptor)

            val viewPager: ViewPager2 = findViewById(R.id.pdfViewPager)
            viewPager.adapter = PdfPageAdapter(this, pdfRenderer)
        } catch (e: IOException) {
            e.printStackTrace()
            // Manejar el error
        }
    }


    // Copia el archivo PDF de los assets al almacenamiento interno, si aún no se ha copiado
    private fun copyFileFromAssets(assetFileName: String, destinationPath: String) {
        try {
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
        } catch (e: IOException) {
            e.printStackTrace()
            // Manejar el error
        }
    }
    private fun downloadPdf() {

        // Verifica si tienes el permiso de escritura en el almacenamiento externo
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            // Si no tienes permiso, muestra un mensaje o solicita el permiso nuevamente (según el flujo de tu app)
            Toast.makeText(this, "Permiso de almacenamiento externo necesario", Toast.LENGTH_LONG).show()
            // Puedes optar por solicitar el permiso aquí o simplemente regresar y no proceder con la descarga
            return
        }
        val assetFileName = "Dietas al alta Ileostomía - Menú de VERANO.pdf"
        val channelId = "download_notification"
        val notificationId = 1

        // Crear el NotificationChannel, pero solo para API 26+ porque
        // la clase NotificationChannel es nueva y no está en la biblioteca de soporte
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.descargar_en_pdf)
            val descriptionText = getString(R.string.descargar_en_pdf)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            // Registrar el canal en el sistema
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(this, channelId).apply {
            setContentTitle("Descargando PDF")
            setContentText("Descarga en progreso")
            setSmallIcon(R.drawable.icono_oscuro)
            setPriority(NotificationCompat.PRIORITY_LOW)
            setOngoing(true)
            // Para versiones de Android menores, es recomendable usar NotificationCompat
            setProgress(0, 0, true)
        }

        val notificationManager = NotificationManagerCompat.from(this)

        // Mostrar notificación de inicio de descarga
        notificationManager.notify(notificationId, builder.build())

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

            // Crear un Intent para abrir el archivo con una aplicación de PDF
            val fileUri = FileProvider.getUriForFile(
                this,
                "${BuildConfig.APPLICATION_ID}.provider", // Asegúrate de que esto coincida con tu autoridad de FileProvider definida en AndroidManifest.xml
                file
            )

            val openIntent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(fileUri, "application/pdf")
                flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            }

            val openPendingIntent: PendingIntent = PendingIntent.getActivity(
                this, 0, openIntent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )

            // Actualizar la notificación para mostrar la descarga completa
            // Actualizar la notificación para mostrar la descarga completa
            builder.setContentText("Descarga completa").setContentTitle("Se ha descargado $fileName.")
                .setProgress(0, 0, false) // Remover el modo indeterminado
                .setOngoing(false) // Remover la notificación de ser "en curso"
                .setContentIntent(openPendingIntent) // Establecer el PendingIntent para abrir el PDF
            notificationManager.notify(notificationId, builder.build())


            Toast.makeText(this, "Se ha descargado $fileName.", Toast.LENGTH_LONG).show()
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Error al descargar el archivo: ${e.message}", Toast.LENGTH_LONG).show()

            // Actualizar la notificación para indicar el fallo en la descarga
            builder.setContentText("Error en la descarga")
                .setProgress(0, 0, false) // Remover el modo indeterminado
                .setOngoing(false) // Remover la notificación de ser "en curso"
            notificationManager.notify(notificationId, builder.build())
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == InfoEstomaActivity.MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                downloadPdf()
            } else {
                Toast.makeText(this, "Permiso de almacenamiento denegado", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
