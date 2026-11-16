package fr.btsciel.androiddefaultprogram

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import fr.btsciel.androiddefaultprogram.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private var taux:Double = 0.0;
    private var bas:Double? = 0.0;
    private var haut:Double? = 0.0;


    private val TAUX_E_D = 1.12;
    private val TAUX_E_Y = 176.66;
    private val TAUX_D_Y = TAUX_E_D / TAUX_E_Y;


    private lateinit var binding: ActivityMainBinding;
    private lateinit var dataForBindingViewModel: DataForBindingViewModel;
    private lateinit var conversionViewModel: ConversionViewModel;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        enableEdgeToEdge();
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //Syncro cycle de vie
        conversionViewModel = ViewModelProvider(this).get(ConversionViewModel::class.java);
        dataForBindingViewModel = ViewModelProvider(this).get(DataForBindingViewModel::class.java);

        binding.infoIhm = dataForBindingViewModel;
        Log.d(":liveData", ":liveData du main: ${dataForBindingViewModel.dataForBinding}");
        binding.lifecycleOwner = this@MainActivity;


        if (conversionViewModel._taux == null){
            binding.rbEuroDollar.isChecked = true;
            mettreAJour(TAUX_E_D, R.string.euro, R.string.dollar);
        }


        binding.rbEuroDollar.setOnClickListener {
            mettreAJour(TAUX_E_D, R.string.euro, R.string.dollar);

        }


        binding.rbDollarYen.setOnClickListener {
            mettreAJour(TAUX_D_Y, R.string.dollar, R.string.yen);

        }


        binding.rbEuroYen.setOnClickListener {
            mettreAJour(TAUX_E_Y, R.string.euro, R.string.yen);

        }



        binding.buttonBas.setOnClickListener {
            Log.d("deb", "click button bas");

            haut = binding.editTextHaut.text.toString().replace(",", ".").toDoubleOrNull();

            if (haut == null){
                Toast.makeText(this,"Mauvaise saisi",Toast.LENGTH_LONG).show();
                return@setOnClickListener;
            }

            dataForBindingViewModel.updateFateForBinding(DataForBinding(resultatHaut = (haut), resultatBas = (haut!! * taux)));
            Toast.makeText(this,"Correct",Toast.LENGTH_LONG).show();

        }

        binding.buttonHaut.setOnClickListener {
            Log.d("deb", "clicked button haut");

            bas = binding.editTextBas.text.toString().replace(",", ".").toDoubleOrNull() ?: 0.0;

            if (bas == null){
                Toast.makeText(this,"Mauvaise saisi",Toast.LENGTH_LONG).show();
                return@setOnClickListener;
            }

            dataForBindingViewModel.updateFateForBinding(DataForBinding(resultatHaut = (bas!! / taux), resultatBas = (bas)));
            Toast.makeText(this,"correct",Toast.LENGTH_LONG).show();
        }

    }


    private fun mettreAJour(taux:Double, euro:Int, dollar:Int){
        this.taux = taux;
        conversionViewModel.modifierTaux(taux);
        dataForBindingViewModel.updateFateForBinding(DataForBinding(labelHaut = getString(euro), labelBas = getString(dollar)));
    }

}