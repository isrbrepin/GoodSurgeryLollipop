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
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.us.goodsurgery.R
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.inflamatoria.estoma.EstomaActivity
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.inflamatoria.reseccion.ReseccionActivity
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.polipos.colon.ColonActivity
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.polipos.recto.RectoActivity
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.reconstruccion.colostomia.ColostomiaActivity
import com.us.goodsurgery.screens.patologias.cirugiaabdominal.reconstruccion.ileostomia.IleostomiaActivity
import com.us.goodsurgery.screens.patologias.proctologia.fistula.FistulaActivity
import com.us.goodsurgery.screens.patologias.proctologia.fisura.FisuraActivity
import com.us.goodsurgery.screens.patologias.proctologia.hemorroides.HemorroidesActivity
import com.us.goodsurgery.screens.patologias.suelopelvico.incontinencia.IncontinenciaActivity
import com.us.goodsurgery.screens.patologias.suelopelvico.prolapso.ProlapsoActivity
import com.us.goodsurgery.screens.patologias.suelopelvico.rectocele.RectoceleActivity


class PrincipalActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        val navigationView = findViewById<NavigationView>(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener(this)

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
        when (item.itemId) {
            R.id.nav_desplegable -> {
                val id = item.itemId
                // Encuentra tu NavigationView
                val navigationView = findViewById<NavigationView>(R.id.navigation_view)
                // Encuentra el menú del NavigationView
                val menu = navigationView.menu
                // Aquí alternas la visibilidad de los elementos de submenú
                val isVisible = menu.findItem(R.id.tumor_de_colon).isVisible
                // Cambias la visibilidad de cada ítem
                menu.findItem(R.id.tumor_de_colon).isVisible = !isVisible
                menu.findItem(R.id.tumor_de_recto).isVisible = !isVisible
                menu.findItem(R.id.ilestomia_lateral).isVisible = !isVisible
                menu.findItem(R.id.cierre_de_colostomia_terminal).isVisible = !isVisible
                menu.findItem(R.id.reseccion_instestinal).isVisible = !isVisible
                menu.findItem(R.id.estoma).isVisible = !isVisible
                menu.findItem(R.id.hemorroides).isVisible = !isVisible
                menu.findItem(R.id.fisura_anal).isVisible = !isVisible
                menu.findItem(R.id.fistula_anal).isVisible = !isVisible
                menu.findItem(R.id.incontinencia).isVisible = !isVisible
                menu.findItem(R.id.rectocele).isVisible = !isVisible
                menu.findItem(R.id.prolapso).isVisible = !isVisible
                // Agrega o remueve ítems según necesites
                return true
            }

            R.id.nav_inicio -> {
                drawerLayout.closeDrawer(GravityCompat.START)
                return true
            }

            R.id.tumor_de_colon -> {
                val intent = Intent(this, ColonActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.tumor_de_recto -> {
                val intent = Intent(this, RectoActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.ilestomia_lateral -> {
                val intent = Intent(this, IleostomiaActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.cierre_de_colostomia_terminal -> {
                val intent = Intent(this, ColostomiaActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.reseccion_instestinal -> {
                val intent = Intent(this, ReseccionActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.estoma -> {
                val intent = Intent(this, EstomaActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.hemorroides -> {
                val intent = Intent(this, HemorroidesActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.fisura_anal -> {
                val intent = Intent(this, FisuraActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.fistula_anal -> {
                val intent = Intent(this, FistulaActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.incontinencia -> {
                val intent = Intent(this, IncontinenciaActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.rectocele -> {
                val intent = Intent(this, RectoceleActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.prolapso -> {
                val intent = Intent(this, ProlapsoActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.nav_acerca_de -> {
                    val dialogView = layoutInflater.inflate(R.layout.activity_acerca_de, null)
                    val builder = AlertDialog.Builder(this, R.style.CustomAlertDialog)
                    builder.setView(dialogView)

                    val dialog = builder.create()

                    val layoutParams = WindowManager.LayoutParams().apply {
                        copyFrom(dialog.window?.attributes)
                        gravity = Gravity.CENTER // Cambiar a la posición que desees
                    }
                    dialog.window?.attributes = layoutParams

                    val dismissButton: ImageButton = dialogView.findViewById(R.id.btn_cerrar_acerca_de)

                    dismissButton.setOnClickListener {
                        // Cierra el AlertDialog
                        dialog.dismiss()
                    }
                    dialog.show()
                return true
            }

        }

        // No resaltar ningún elemento como seleccionado
        return false
    }
}