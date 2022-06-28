package co.edu.unab.mgads.lpacheco.storeapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import co.edu.unab.mgads.lpacheco.storeapp.viewmodel.ProductListActivityViewModel
import co.edu.unab.mgads.lpacheco.storeapp.R
import co.edu.unab.mgads.lpacheco.storeapp.databinding.ActivityProductListBinding

class ProductListActivity : AppCompatActivity() {

    lateinit var bindind: ActivityProductListBinding
    lateinit var viewModel : ProductListActivityViewModel

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

        bindind.viewModel = viewModel

        viewModel.loadProducts()

        viewModel.refreshData()

        viewModel.adapter.onItemClickListener = {
            System.out.println(it.name)

            val intentDetail = Intent(applicationContext, ProductDetailActivity::class.java)
            intentDetail.putExtra("product", it)
            startActivity(intentDetail)


        }
    }
}