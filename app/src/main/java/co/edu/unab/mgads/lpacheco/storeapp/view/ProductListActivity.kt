package co.edu.unab.mgads.lpacheco.storeapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import co.edu.unab.mgads.lpacheco.storeapp.viewmodel.ProductListActivityViewModel
import co.edu.unab.mgads.lpacheco.storeapp.R
import co.edu.unab.mgads.lpacheco.storeapp.databinding.ActivityProductListBinding
import co.edu.unab.mgads.lpacheco.storeapp.model.entity.Product

class ProductListActivity : AppCompatActivity() {

    lateinit var bindind: ActivityProductListBinding
    lateinit var viewModel : ProductListActivityViewModel
    lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        var message:String?= intent.getStringExtra("message")
        var data:String?= intent.getStringExtra("data")

        message?.let {
            title = "$message $data"
        }


        bindind = DataBindingUtil.setContentView(this, R.layout.activity_product_list)

        viewModel = ViewModelProvider(this)[ProductListActivityViewModel::class.java]

        adapter = ProductAdapter(arrayListOf())
        bindind.adapter = adapter

        viewModel.products.observe(this, {
            if (it.isEmpty()){
                viewModel.loadFakeDake()
            }
            adapter.refresh(it as MutableList<Product>)
        })


        adapter.onItemClickListener = {
            System.out.println(it.toString())
            val intentDetail = Intent(applicationContext, ProductDetailActivity::class.java)
            intentDetail.putExtra("product_key", it.key)
            startActivity(intentDetail)
        }

        adapter.onItemLongClickListener = {
            viewModel.deleteProduct(it)
            // adapter.refresh(viewModel.products)
        }

        bindind.btnAddProduct.setOnClickListener {
            startActivity(Intent(applicationContext, ProductAddActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        // viewModel.loadProducts()
        // adapter.refresh(viewModel.products)
    }
}