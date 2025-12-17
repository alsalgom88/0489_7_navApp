package com.example.navapp
// Paquet base per mantenir l’organització del projecte.


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
// Per controlar els menús superiors i les seves accions.

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
// Llibreries de Navigation Component per sincronitzar toolbar, títols i navegació.

import androidx.appcompat.widget.Toolbar
// Toolbar moderna que substitueix l'ActionBar clàssica.


class OptionsActivity : AppCompatActivity() {

    // Controlador de navegació (gestiona quin fragment es mostra)
    private lateinit var navController: NavController

    // Configuració de l’AppBar (gestiona icona enrere, títols, etc.)
    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_options)
        // Assignem el layout de l’Activity amb la Toolbar i NavHost.


        // -----------------------------------------------------------
        // 1) TOOLBAR
        // -----------------------------------------------------------

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        /*
            Substituïm l'ActionBar per una Toolbar personalitzada.
            Això ens permet:
            ✔ Mostrar títols automàticament
            ✔ Mostrar icona enrere
            ✔ Mostrar menú superior inflat des de XML
         */


        // -----------------------------------------------------------
        // 2) NAVHOST (fragment contenidor per a la navegació)
        // -----------------------------------------------------------

        val navHost = supportFragmentManager
            .findFragmentById(R.id.nav_host_options) as NavHostFragment

        navController = navHost.navController
        /*
            NavHostFragment conté els fragments de la Navigation Graph.
            navController gestiona cap a quin fragment es navega.
         */


        // -----------------------------------------------------------
        // 3) AppBarConfiguration
        // -----------------------------------------------------------

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.options1Fragment,
                R.id.options2Fragment,
                R.id.options3Fragment
            )
        )
        /*
            Aquests fragments es consideren "top-level destinations":
            - No mostren fletxa enrere
            - Mostren només el títol
            Són com pantalles principals dins d’aquesta secció.
         */


        // -----------------------------------------------------------
        // 4) Toolbar + NavController
        // -----------------------------------------------------------

        NavigationUI.setupActionBarWithNavController(
            this,
            navController,
            appBarConfiguration
        )
        /*
            Sincronitza la Toolbar amb el NavController:
            ✔ El títol de la Toolbar canvia segons el fragment actual
            ✔ Es mostra icona enrere quan cal
         */
    }


    // -----------------------------------------------------------
    // 5) INFLAR EL MENÚ SUPERIOR (options_menu.xml)
    // -----------------------------------------------------------

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
        /*
            Aquest mètode infla el menú XML:
            res/menu/options_menu.xml

            ⮞ Aquí és on apareixen els botons:
               • Settings
               • Help
               • About
         */
    }


    // -----------------------------------------------------------
    // 6) GESTIONAR QUAN L’USUARI PREM UNA OPCIÓ
    // -----------------------------------------------------------

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            // Quan l’usuari prem “Settings”
            R.id.menu_settings -> {
                navController.navigate(R.id.options2Fragment)
                true
            }

            // Quan prem “Help”
            R.id.menu_help -> {
                navController.navigate(R.id.options3Fragment)
                true
            }

            // Quan prem “About”
            R.id.menu_about -> {
                navController.navigate(R.id.options1Fragment)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
        /*
            Aquest switch controla la navegació segons el botó del menú.
            Navigation Component fa la navegació entre fragments automàticament.
         */
    }


    // -----------------------------------------------------------
    // 7) GESTIÓ DEL BOTÓ “ENRERE” A LA TOOLBAR
    // -----------------------------------------------------------

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
        /*
            Controla què passa quan es prem la fletxa enrere a la Toolbar.
            Si Navigation Component pot navegar enrere → ho fa.
            Si no, es crida al comportament predeterminat.
         */
    }
}
