package co.edu.unab.mgads.lpacheco.storeapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import co.edu.unab.mgads.lpacheco.storeapp.R
import co.edu.unab.mgads.lpacheco.storeapp.databinding.ActivityPruebaBinding

class PruebaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPruebaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prueba)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_prueba)


        binding.button.setOnClickListener{
            System.out.println("Datos")

        }


    }
}