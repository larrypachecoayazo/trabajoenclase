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
    lateinit var myProduct: Product


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_add)

        myProduct = Product(name = "", price = 0)

        viewModel = ViewModelProvider(this)[ProductAddActivityViewModel::class.java]

        binding.btnAddProductBtnAgregar.setOnClickListener {
            viewModel.add(binding.product)
            finish()
        }



    }
}