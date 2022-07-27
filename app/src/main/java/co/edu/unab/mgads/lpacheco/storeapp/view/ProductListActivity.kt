package co.edu.unab.mgads.lpacheco.storeapp.view

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

        loadProducts()


        adapter.onItemClickListener = {
            val intentDetail = Intent(applicationContext, ProductDetailActivity::class.java)
            intentDetail.putExtra("product_key", it.key)
            intentDetail.putExtra("product_id", it.id)
            startActivity(intentDetail)
        }

        adapter.onItemLongClickListener = {
            viewModel.deleteProduct(it).observe(this){ state ->
                if (state){
                    Toast.makeText(applicationContext, "Producto eliminado", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(applicationContext, "Error al eliminar un producto", Toast.LENGTH_SHORT).show()
                }
            }
        }

        bindind.btnAddProduct.setOnClickListener {
            startActivity(Intent(applicationContext, ProductAddActivity::class.java))
        }
    }

    private fun loadProducts(){
        viewModel.loadProducts()
        viewModel.products.observe(this) {
            if (it.isEmpty()){
                viewModel.loadFakeDake()
            }
            adapter.refresh(it as ArrayList<Product>)
        }
    }

    override fun onResume() {

        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_logout -> {
                logout()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun logout(){
        val preferences: SharedPreferences = getSharedPreferences("store_app.pref", MODE_PRIVATE)
        var editor = preferences.edit()
        editor.clear()
        editor.apply()

        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

}