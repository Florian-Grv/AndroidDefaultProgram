package fr.btsciel.androiddefaultprogram

import androidx.lifecycle.ViewModel

class ConversionViewModel : ViewModel() {

    var _taux:Double? = null
        private set;

    fun modifierTaux (nouveauTaux : Double?){
        _taux = nouveauTaux;
    }
}