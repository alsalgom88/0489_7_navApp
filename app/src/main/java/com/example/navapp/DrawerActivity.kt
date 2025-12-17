package com.example.navapp
// Paquet on es troba aquesta Activity per organitzar el codi del projecte.


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
// Activity base que permet compatibilitat total amb Material Design.

import androidx.drawerlayout.widget.DrawerLayout
// Classe que gestiona el panell lateral (navigation drawer).

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
// NavHostFragment conté i gestiona la navegació entre fragments.

import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
// Permet enllaçar la Toolbar, el Drawer i el NavController.

import com.google.android.material.navigation.NavigationView
// Vista lateral on apareix el menú (els ítems del drawer).

import androidx.appcompat.widget.Toolbar
// Toolbar moderna que substituirà l’ActionBar clàssica.


class DrawerActivity : AppCompatActivity() {

    // Controlador principal de navegació
    private lateinit var navController: NavController

    // Configuració de la Toolbar + Drawer juntament amb navegació
    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        // Es crida quan l’Activity es crea.
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)
        // Assignem el layout principal que conté DrawerLayout, Toolbar i NavHostFragment.


        // -----------------------------------------------------------
        // 1) REFERÈNCIES ALS ELEMENTS XML
        // -----------------------------------------------------------

        // Rebem la Toolbar definida a activity_drawer.xml
        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        // Contenidor principal del navigation drawer
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)

        // Vista lateral on hi ha el menú (navigation menu)
        val navView = findViewById<NavigationView>(R.id.nav_view)


        // -----------------------------------------------------------
        // 2) CONFIGURAR LA TOOLBAR
        // -----------------------------------------------------------

        // Substituïm l’ActionBar per la Toolbar moderna
        setSupportActionBar(toolbar)
        // Això permet controlar el títol, la icona hamburguesa i el botó enrere.


        // -----------------------------------------------------------
        // 3) RECUPERAR EL NavController
        // -----------------------------------------------------------

        // NavHostFragment és el contenidor dels fragments navegables
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        // Obtenim el NavController per gestionar navegació entre Fragments
        navController = navHostFragment.navController


        // -----------------------------------------------------------
        // 4) TOP-LEVEL DESTINATIONS
        // -----------------------------------------------------------

        /*
            Són pantalles principals del Drawer.
            Quan el fragment actual és un “top-level destination”
            ➜ La icona de la Toolbar és la HAMBURGUESA (≡)
            Quan NO és top-level:
            ➜ La icona és una FLETXA enrere (←)
         */

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.drawer1Fragment,
                R.id.drawer2Fragment,
                R.id.drawer3Fragment
            ),
            drawerLayout // ❗ Important: s’ha d’enllaçar amb el DrawerLayout real
        )


        // -----------------------------------------------------------
        // 5) ENLLAÇAR LA TOOLBAR → NAVCONTROLLER
        // -----------------------------------------------------------

        /*
            Això sincronitza:
            ✔ el títol de la Toolbar amb el fragment actual
            ✔ la icona hamburguesa / enrere
            ✔ l’animació d’obrir / tancar el Drawer
         */
        NavigationUI.setupActionBarWithNavController(
            this,
            navController,
            appBarConfiguration
        )


        // -----------------------------------------------------------
        // 6) ENLLAÇAR NavigationView (menú lateral) → NAVCONTROLLER
        // -----------------------------------------------------------

        /*
            Quan l’usuari prem un ítem del menú del drawer:
            ➜ NavController navegarà automàticament al fragment assignat.
         */
        NavigationUI.setupWithNavController(navView, navController)
    }


    // -----------------------------------------------------------
    // 7) GESTIÓ de la icona (hamburguesa / fletxa enrere)
    // -----------------------------------------------------------

    override fun onSupportNavigateUp(): Boolean {
        // Gestiona el botó de tornada segons AppBarConfiguration
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}
