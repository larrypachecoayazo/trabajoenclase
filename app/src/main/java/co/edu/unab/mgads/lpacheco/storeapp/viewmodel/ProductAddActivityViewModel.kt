package co.edu.unab.mgads.lpacheco.storeapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import co.edu.unab.mgads.lpacheco.storeapp.model.entity.Product
import co.edu.unab.mgads.lpacheco.storeapp.model.repository.ProductRepository

class ProductAddActivityViewModel(application: Application): AndroidViewModel(application) {

    private val productRepository:ProductRepository = ProductRepository(application)
    var product = Product(name = "", price = 0)

    fun add(){
        productRepository.addLocal(product)
    }
}