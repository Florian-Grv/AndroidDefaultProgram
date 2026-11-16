package fr.btsciel.androiddefaultprogram;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

class DataForBindingViewModel : ViewModel() {

    private val _dataForBinding = MutableLiveData<DataForBinding>();
    val dataForBinding: LiveData<DataForBinding> = _dataForBinding;

    init {
        _dataForBinding.value = DataForBinding( labelHaut = "Euro", labelBas = "Dollar",0.0, 0.0);
    }

    fun updateFateForBinding(newData : DataForBinding) {

        _dataForBinding.value?.let{oldData ->
            val data = oldData.copy(
                labelHaut = newData.labelHaut ?: oldData.labelHaut,
                labelBas = newData.labelBas ?: oldData.labelBas,
                resultatHaut = newData.resultatHaut ?: oldData.resultatHaut,
                resultatBas = newData.resultatBas ?: oldData.resultatBas,
            );
            _dataForBinding.value = data;
            Log.d("liveData", dataForBinding.value.toString());
        };

    }



}