package fr.btsciel.androiddefaultprogram

data class DataForBinding(
    var labelHaut : String? = null,
    var labelBas : String? = null,

    var resultatHaut:Double? = null,
    var resultatBas:Double? = null
){
    val resultatBasFormatted: String
        get() = String.format("%.2f", resultatBas ?: 0.0)
    val resultatHautFormatted: String
        get() = String.format("%.2f", resultatHaut ?: 0.0)
}
