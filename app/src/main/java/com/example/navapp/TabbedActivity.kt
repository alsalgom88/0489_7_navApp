package com.example.navapp
// Paquet on es troba aquesta Activity dins del projecte.


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
// Toolbar moderna que substituirà l'ActionBar tradicional.

import com.google.android.material.tabs.TabLayout
// Widget de Material Design que mostra pestanyes (tabs).

import com.google.android.material.tabs.TabLayoutMediator
// Classe clau que sincronitza TabLayout ↔ ViewPager2.

import androidx.viewpager2.widget.ViewPager2
// Vista que permet fer swipes entre fragments/pantalles.


class TabbedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // Mètode que s'executa quan l'activitat és creada.
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabbed)
        // Assigna com a disseny principal l’XML activity_tabbed.xml.


        // ------------------------------------------------------------
        // 1) CONFIGURACIÓ DE LA TOOLBAR
        // ------------------------------------------------------------
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        /*
            Substituïm l’ActionBar per una Toolbar personalitzada.
            Permet:
            - mostrar títol automàticament,
            - mostrar icona back si cal,
            - integració amb Material Design.
         */


        // ------------------------------------------------------------
        // 2) REFERÈNCIES ALS COMPONENTS TABS + VIEWPAGER
        // ------------------------------------------------------------
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)

        /*
            TabLayout = pestanyes visibles a la part superior.
            ViewPager2 = gestor de pàgines que permet fer swipe esquerra/dreta.
            TabLayout i ViewPager2 han de treballar sincronitzats.
         */


        // ------------------------------------------------------------
        // 3) ADAPTADOR DEL VIEWPAGER
        // ------------------------------------------------------------
        val adapter = TabsPagerAdapter(this)
        viewPager.adapter = adapter

        /*
            TabsPagerAdapter és un adaptador personalitzat que has creat tu.
            Normalment:
               ✔ retorna 3 fragments (Inici, Llista, Perfil)
               ✔ 1 fragment per cada posició del ViewPager
         */


        // ------------------------------------------------------------
        // 4) SINCRONITZAR TABS ↔ VIEWPAGER2
        // ------------------------------------------------------------
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->

            // Assignar el text de cada pestanya segons la posició
            when(position) {
                0 -> tab.text = "Inici"   // Primera pestanya
                1 -> tab.text = "Llista"  // Segona pestanya
                2 -> tab.text = "Perfil"  // Tercera pestanya
            }

        }.attach()
        /*
            TabLayoutMediator:
                - sincronitza el ViewPager2 amb el TabLayout
                - quan canvies de tab → canvia de pàgina
                - quan fas swipe → canvia la pestanya seleccionada

            attach() finalitza la configuració i activa el vincle.
         */
    }
}
