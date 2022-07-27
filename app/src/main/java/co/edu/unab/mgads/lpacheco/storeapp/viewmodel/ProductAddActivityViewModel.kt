package co.edu.unab.mgads.lpacheco.storeapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import co.edu.unab.mgads.lpacheco.storeapp.model.entity.Product
import co.edu.unab.mgads.lpacheco.storeapp.model.repository.ProductRepository

class ProductAddActivityViewModel(application: Application): AndroidViewModel(application) {

    private val productRepository:ProductRepository = ProductRepository(application)
    var product = Product(name = "", price = 0)

    fun add(): LiveData<String> {
        // productRepository.addLocal(product)
        return productRepository.addFirestone(product)
        // return productRepository.addAPI(product)
    }

    fun edit(): LiveData<Boolean> {
        // productRepository.updateLocal(product)
        return productRepository.updateFirestone(product)
        // return productRepository.updateAPI(product)
    }


}