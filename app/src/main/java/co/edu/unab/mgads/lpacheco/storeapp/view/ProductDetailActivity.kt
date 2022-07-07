package co.edu.unab.mgads.lpacheco.storeapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import co.edu.unab.mgads.lpacheco.storeapp.R
import co.edu.unab.mgads.lpacheco.storeapp.databinding.ActivityProductDetailBinding
import co.edu.unab.mgads.lpacheco.storeapp.viewmodel.ProductDetailActivityViewModel

class ProductDetailActivity : AppCompatActivity() {

    lateinit var binding:ActivityProductDetailBinding
    lateinit var viewModel: ProductDetailActivityViewModel
    private var myProductId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        myProductId = intent.getIntExtra("product_key", 0)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail)

        viewModel = ViewModelProvider(this)[ProductDetailActivityViewModel::class.java]
        viewModel.getProductByKey(myProductId)

        binding.product = viewModel.product

        binding.btnProductDetailEdit.setOnClickListener {
            val intentAdd = Intent(applicationContext, ProductAddActivity::class.java)
            intentAdd.putExtra("product", viewModel.product)
            startActivity(intentAdd)
        }

        binding.btnProductDetailReturn.setOnClickListener {
            finish()
        }

    }

    override fun onResume() {
        viewModel.getProductByKey(myProductId)
        binding.product = viewModel.product
        super.onResume()
    }
}