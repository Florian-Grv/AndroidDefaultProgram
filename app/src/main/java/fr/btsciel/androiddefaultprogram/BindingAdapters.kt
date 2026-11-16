package fr.btsciel.androiddefaultprogram

import android.widget.EditText
import androidx.databinding.BindingAdapter

@BindingAdapter("formattedDouble")
fun setFormattedDouble(editText: EditText, value: Double) {
    value?.let { editText.setText(String.format("%.2f", it)) }
}