package com.example.navapp
// Paquet on es troba l’adaptador que controla el ViewPager2 de TabbedActivity.


import androidx.fragment.app.Fragment
// Classe base que usarem per retornar fragments segons la pestanya seleccionada.

import androidx.fragment.app.FragmentActivity
// Necessari perquè FragmentStateAdapter treballa dins d’una Activity que pot contenir fragments.

import androidx.viewpager2.adapter.FragmentStateAdapter
// Adaptador oficial per ViewPager2. Gestiona fragments i el seu estat de manera eficient.


class TabsPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    /*
        Aquest adaptador és responsable de:
        - Quantes pàgines té el ViewPager2
        - Quin fragment s’ha de mostrar en cada posició (0, 1, 2)

        S'utilitza a TabbedActivity per mostrar 3 pantalles:
        ✔ Tabbed1AFragment
        ✔ Tabbed2AFragment
        ✔ Tabbed3AFragment
    */


    override fun getItemCount(): Int = 3
    /*
        Indica el nombre total de pestanyes o pàgines del ViewPager2.

        En aquest cas → 3 fragments

        Posicions:
        0 = primera pestanya
        1 = segona pestanya
        2 = tercera pestanya
    */


    override fun createFragment(position: Int): Fragment {
        /*
            Aquesta funció retorna el fragment que s’ha de mostrar
            segons la posició que el ViewPager2 està demanant.

            És cridada automàticament quan l’usuari:
            - fa swipe esquerra/dreta
            - prem una pestanya del TabLayout
        */

        return when(position) {

            0 -> Tabbed1AFragment()
            // Fragment de la pestanya “Inici” o la primera pantalla

            1 -> Tabbed2AFragment()
            // Fragment de la pestanya “Llista”

            2 -> Tabbed3AFragment()
            // Fragment de la pestanya “Perfil”

            else -> Tabbed1AFragment()
            /*
                Cas "per seguretat":
                Si per algun motiu la posició no és 0–2 (no hauria de passar)
                tornem el primer fragment per evitar errors.
             */
        }
    }
}
