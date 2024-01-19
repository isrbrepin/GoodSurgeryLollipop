package com.us.goodsurgery.screens


import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.us.goodsurgery.R


class PrincipalActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        // Encuentra el ImageButton por su ID
        val drawerButton: ImageButton = findViewById(R.id.menu_drawer)
        drawerLayout = findViewById(R.id.drawer_layout)

        // Configura el click listener para abrir el drawer
        drawerButton.setOnClickListener {
            // Abre el drawer
            drawerLayout.openDrawer(GravityCompat.START)
        }


        //-----------------------------------------------------------------------------------------

        val btnComenzar: Button = findViewById(R.id.btn_comenzar)
        // Configura el evento de clic del botón
        btnComenzar.setOnClickListener {
            // Crear una intención para lanzar la SecondActivity
            val intent = Intent(this@PrincipalActivity, PatologiaActivity::class.java)
            startActivity(intent)
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
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (toggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.nav_inicio) {
            // Maneja el click para "Inicio"
        } else if (id == R.id.nav_desplegable) {
            // Maneja el click para "Patologías"
        } else if (id == R.id.nav_acerca_de) {
            // Maneja el click para "Acerca de"
        }
        // Cierra el drawer cuando se selecciona un item
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}