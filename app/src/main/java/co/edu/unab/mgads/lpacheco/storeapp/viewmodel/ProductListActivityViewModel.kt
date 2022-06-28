package co.edu.unab.mgads.lpacheco.storeapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import co.edu.unab.mgads.lpacheco.storeapp.view.ProductAdapter
import co.edu.unab.mgads.lpacheco.storeapp.model.entity.Product
import co.edu.unab.mgads.lpacheco.storeapp.model.repository.ProductRepository

class ProductListActivityViewModel(application: Application): AndroidViewModel(application) {

    private var products: MutableList<Product> = mutableListOf()
    var adapter: ProductAdapter = ProductAdapter(products)
    private val productRepository:ProductRepository = ProductRepository(application)

    fun loadProducts(){

        products = productRepository.getAllLocal()

    }

    fun refreshData(){
        adapter.refresh(products)
    }

}