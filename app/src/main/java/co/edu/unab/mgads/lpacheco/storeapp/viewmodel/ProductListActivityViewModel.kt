package co.edu.unab.mgads.lpacheco.storeapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import co.edu.unab.mgads.lpacheco.storeapp.view.ProductAdapter
import co.edu.unab.mgads.lpacheco.storeapp.model.entity.Product
import co.edu.unab.mgads.lpacheco.storeapp.model.repository.ProductRepository

class ProductListActivityViewModel(application: Application): AndroidViewModel(application) {

    var products: MutableList<Product> = mutableListOf()

    private val productRepository:ProductRepository = ProductRepository(application)

    init {
        loadProducts()
    }

    fun loadProducts(){
        products = productRepository.getAllLocal()
    }

    fun deleteProduct(myProduct:Product){
        productRepository.deleteLocal(myProduct)
        loadProducts()
    }



}