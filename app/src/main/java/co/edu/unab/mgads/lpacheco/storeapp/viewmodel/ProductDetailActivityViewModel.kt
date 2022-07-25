package co.edu.unab.mgads.lpacheco.storeapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import co.edu.unab.mgads.lpacheco.storeapp.model.entity.Product
import co.edu.unab.mgads.lpacheco.storeapp.model.repository.ProductRepository

class ProductDetailActivityViewModel(application: Application): AndroidViewModel(application) {

    private val productRepository:ProductRepository = ProductRepository(application)
    lateinit var product: LiveData<Product>

    fun getProductByKey(myProductKey: Int) {
        productRepository.getByKeyLocal(myProductKey)
        product = productRepository.productObserver
    }

    fun getProductById(myProductId: String) {
        productRepository.getByIdFirestone(myProductId)
        product = productRepository.productObserver
    }

}