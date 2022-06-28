package co.edu.unab.mgads.lpacheco.storeapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import co.edu.unab.mgads.lpacheco.storeapp.R
import co.edu.unab.mgads.lpacheco.storeapp.databinding.ActivityProductDetailBinding
import co.edu.unab.mgads.lpacheco.storeapp.model.Product
import co.edu.unab.mgads.lpacheco.storeapp.model.ProductDetailActivityViewModel

class ProductDetailActivity : AppCompatActivity() {

    lateinit var binding:ActivityProductDetailBinding
    lateinit var viewModel:ProductDetailActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val myProduct:Product = intent.getSerializableExtra("product") as Product

        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail)

        viewModel = ViewModelProvider(this)[ProductDetailActivityViewModel::class.java]
        viewModel.product = myProduct

        binding.product = viewModel.product

    }
}