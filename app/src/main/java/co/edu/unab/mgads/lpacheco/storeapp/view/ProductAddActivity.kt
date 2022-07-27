package co.edu.unab.mgads.lpacheco.storeapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import co.edu.unab.mgads.lpacheco.storeapp.R
import co.edu.unab.mgads.lpacheco.storeapp.databinding.ActivityProductAddBinding
import co.edu.unab.mgads.lpacheco.storeapp.model.entity.Product
import co.edu.unab.mgads.lpacheco.storeapp.viewmodel.ProductAddActivityViewModel

class ProductAddActivity : AppCompatActivity() {

    lateinit var binding:ActivityProductAddBinding
    lateinit var viewModel: ProductAddActivityViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val myProduct:Product?= intent.getSerializableExtra("product") as Product?

        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_add)

        viewModel = ViewModelProvider(this)[ProductAddActivityViewModel::class.java]

        // binding.viewModel = viewModel

        myProduct?.let {

            binding.tvProductAddTitulo.text = "Editar producto"
            binding.btnAddProductBtnAgregar.text = "Actualizar"

            viewModel.product = it
            binding.btnAddProductBtnAgregar.setOnClickListener {

                viewModel.edit()
                finish()
            }

        }?:run{
            binding.btnAddProductBtnAgregar.setOnClickListener {
                viewModel.add()
                finish()
            }
        }



    }
}