package co.edu.unab.mgads.lpacheco.storeapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import co.edu.unab.mgads.lpacheco.storeapp.R
import co.edu.unab.mgads.lpacheco.storeapp.databinding.ActivityProductDetailBinding
import co.edu.unab.mgads.lpacheco.storeapp.model.entity.Product
import co.edu.unab.mgads.lpacheco.storeapp.viewmodel.ProductDetailActivityViewModel

class ProductDetailActivity : AppCompatActivity() {

    lateinit var binding:ActivityProductDetailBinding
    lateinit var viewModel: ProductDetailActivityViewModel

    private var myProductKey:Int = 0
    private var myProductId:String ?= ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        myProductKey = intent.getIntExtra("product_key", 0)
        myProductId = intent.getStringExtra("product_id")


        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail)

        viewModel = ViewModelProvider(this)[ProductDetailActivityViewModel::class.java]


        //viewModel.getProductByKey(myProductKey)
        myProductId?.let { viewModel.getProductById(it) }
        viewModel.product.observe(this){
            binding.product = it

            /*
            it?.let {
                binding.product = it
            }?:run {
                binding.product = Product(name="", price = 0)
            }
            */

        }

        // binding.product = viewModel.product.value

        binding.btnProductDetailEdit.setOnClickListener {
            val intentAdd = Intent(applicationContext, ProductAddActivity::class.java)
            intentAdd.putExtra("product", binding.product)
            startActivity(intentAdd)
        }

        binding.btnProductDetailReturn.setOnClickListener {
            finish()
        }

    }

    override fun onResume() {
        println("onResume()")
        // viewModel.getProductByKey(myProductId)
        myProductId?.let {viewModel.getProductById(it)}
        super.onResume()
    }
}