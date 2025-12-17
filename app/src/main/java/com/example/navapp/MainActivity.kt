package com.example.navapp
// Paquet on es troba aquesta Activity. És important per mantenir el projecte ordenat.


import android.content.Intent
// Intent s'utilitza per navegar entre pantalles (Activities).

import android.os.Bundle
// Bundle conté dades del cicle de vida de l’Activity.

import android.view.View
import android.widget.Button
// Necessari per accedir i manipular botons del layout XML.

import androidx.activity.enableEdgeToEdge
// Funció moderna d’Android que permet que la UI ocupi tota la pantalla,
// sota la barra d'estat i la barra de navegació.

import androidx.appcompat.app.AppCompatActivity
// Classe base per a activitats amb compatibilitat Material Design.

import com.example.navapp.databinding.ActivityMainBinding
// *GENERAT automàticament* si tenim ViewBinding activat.
// En aquest cas, tot i importar-lo, realment **no s'està utilitzant**.


class MainActivity : AppCompatActivity() {
    // Classe principal que gestiona la pantalla inicial de la NavApp.


    override fun onCreate(savedInstanceState: Bundle?) {
        // Mètode que s'executa quan l'Activity es crea per primera vegada.
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        // Aquí carreguem el layout activity_main.xml a la pantalla.

        enableEdgeToEdge()
        // Activa mode "edge-to-edge": la UI aprofita tota la pantalla
        // passant per sota la status bar i navigation bar.


        // ----------------------------------------------------------------------
        // 🧭 NAVEGACIÓ ENTRE ACTIVITIES
        // Cada botó obre una pantalla diferent de la NavApp mitjançant un Intent.
        // ----------------------------------------------------------------------

        // 🔹 Botó per anar a DrawerActivity (menú lateral)
        findViewById<Button>(R.id.action_goto_drawerActivity).setOnClickListener {
            startActivity(Intent(this, DrawerActivity::class.java))
        }


        // 🔹 Botó per anar a BottomActivity (Bottom Navigation)
        findViewById<Button>(R.id.action_goto_bottomActivity).setOnClickListener {
            startActivity(Intent(this, BottomActivity::class.java))
        }


        // 🔹 Botó per anar a OptionsActivity (Menu superior / Toolbar menu)
        findViewById<Button>(R.id.action_goto_optionsActivity).setOnClickListener {
            startActivity(Intent(this, OptionsActivity::class.java))
        }


        // 🔹 Botó per anar a TabbedActivity (pestanyes / ViewPager)
        findViewById<Button>(R.id.action_goto_tabbedActivity).setOnClickListener {
            startActivity(Intent(this, TabbedActivity::class.java))
        }
    }
}
