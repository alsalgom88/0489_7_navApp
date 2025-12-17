package com.example.navapp
// Aquest paquet conté totes les pantalles i activitats relacionades
// amb els sistemes de navegació de la NavApp.


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
// Activitat base amb compatibilitat Material Design.

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
// NavHostFragment és el contenidor dels fragments del navigation graph.

import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
// Llibreries que sincronitzen la Toolbar, BottomNavigationView i NavController.

import com.google.android.material.bottomnavigation.BottomNavigationView
// Vista de Material Design que mostra la barra inferior de navegació (tabs).


class BottomActivity : AppCompatActivity() {

    // Controlador principal de navegació
    private lateinit var navController: NavController

    // Configuració de la barra superior (gestiona icona enrere/hamburguesa i títols)
    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Assignem el layout activity_bottom.xml com a pantalla principal
        setContentView(R.layout.activity_bottom)


        // ---------------------------------------------------------
        // 1) CONFIGURAR LA TOOLBAR
        // ---------------------------------------------------------
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        /*
            Substituïm l'ActionBar per una Toolbar personalitzada.
            Permet:
            ✔ Mostrar títols segons el fragment actual
            ✔ Mostrar la icona enrere si no és top-level
         */


        // ---------------------------------------------------------
        // 2) REFERÈNCIA AL BottomNavigationView
        // ---------------------------------------------------------
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        /*
            bottom_nav és la barra inferior que permet navegar entre:
            - bottom1Fragment
            - bottom2Fragment
            - bottom3Fragment

            Tot queda sincronitzat amb Navigation Component.
         */


        // ---------------------------------------------------------
        // 3) OBTENIR EL NavController
        // ---------------------------------------------------------
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.bottom_nav_host) as NavHostFragment

        navController = navHostFragment.navController
        /*
            NavController gestiona tota la navegació entre fragments.
            bottom_nav_host és el contenidor dels fragments del navigation graph
            associat al BottomNavigationView.
         */


        // ---------------------------------------------------------
        // 4) DEFINIR ELS TOP-LEVEL DESTINATIONS
        // ---------------------------------------------------------
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.bottom1Fragment,
                R.id.bottom2Fragment,
                R.id.bottom3Fragment
            )
        )
        /*
            Aquests fragments són "top-level":

            ✔ No mostren fletxa d’enrere a la Toolbar
            ✔ Són la base del menú inferior
            ✔ Quan canviem entre ells, el títol també canvia automàticament

            Tots ells formen la navegació principal del BottomNavigationView.
         */


        // ---------------------------------------------------------
        // 5) SINCRONITZAR TOOLBAR ↔ NAVCONTROLLER
        // ---------------------------------------------------------
        NavigationUI.setupActionBarWithNavController(
            this, navController, appBarConfiguration
        )
        /*
            Gràcies a això:
            - La Toolbar mostra el títol segons el fragment actual
            - Mostra icona enrere o no segons l’AppBarConfiguration
         */


        // ---------------------------------------------------------
        // 6) SINCRONITZAR BottomNavigationView ↔ NAVCONTROLLER
        // ---------------------------------------------------------
        NavigationUI.setupWithNavController(bottomNav, navController)
        /*
            Això fa que quan l’usuari prem a la barra inferior:
            ✔ NavController canviï al fragment associat
            ✔ Es marqui l’element seleccionat correctament
            ✔ El títol de la Toolbar s’actualitzi

            I viceversa: si fem navegació programàtica, també actualitza el BottomNavigationView.
         */
        bottomNav.setOnItemSelectedListener { item ->
            val view = bottomNav.findViewById<View>(item.itemId)
            view.animate()
                .scaleX(1.15f)
                .scaleY(1.15f)
                .setDuration(120)
                .withEndAction {
                    view.animate().scaleX(1f).scaleY(1f).duration = 120
                }
            navController.navigate(item.itemId)
            true
        }

    }


    // ---------------------------------------------------------
    // 7) GESTIÓ DE LA FLETXA ENRERE A LA TOOLBAR
    // ---------------------------------------------------------
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() ||
                super.onSupportNavigateUp()
        /*
            Gestiona l'acció quan premem el botó enrere de la Toolbar.

            - Si NavController pot fer navigateUp() → ho fa
            - Sinó, fa la lògica d'enrere estàndard de l’Activity
         */
    }
}
