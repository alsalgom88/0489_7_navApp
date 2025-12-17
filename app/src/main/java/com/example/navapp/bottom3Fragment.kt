package com.example.navapp
// Paquet on es troba aquest fragment dins del projecte NavApp.


import android.os.Bundle
import androidx.fragment.app.Fragment
// Fragment: una part modular d’una UI que viu dins d’una Activity.

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
// LayoutInflater = utilitzat per "inflar" (crear) la vista XML del fragment.
// View i ViewGroup = representen elements visuals d’Android.


class Bottom3Fragment : Fragment() {
    /*
        Aquest Fragment representa una de les pantalles gestionades
        pel BottomNavigationView (la tercera pestanya).

        Cada fragment té el seu propi layout i la seva pròpia lògica.
        En aquest cas, el fragment és senzill i només mostra layout estàtic.
    */


    override fun onCreateView(
        inflater: LayoutInflater,     // Objecte que infla el layout XML
        container: ViewGroup?,        // Vista pare on s’insereix el fragment
        savedInstanceState: Bundle?   // Estat anterior (si existeix)
    ): View? {

        // Retorna la vista inflada amb el layout XML corresponent.
        // Aquest serà l'arbre de vistes que es mostrarà a la pantalla.
        return inflater.inflate(R.layout.fragment_bottom3, container, false)

        /*
            El layout fragment_bottom3.xml definirà la UI del fragment:
            text, botons, imatges o qualsevol component.

            L’inflater converteix el XML → objectes View reals.
            El container indica on es col·locarà el fragment dins del NavHostFragment.

            El paràmetre attachToRoot = false és l’opció correcta perquè
            NavHost s'encarrega automàticament d’afegir la vista.
         */
    }
}
