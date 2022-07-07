package co.edu.unab.mgads.lpacheco.storeapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import co.edu.unab.mgads.lpacheco.storeapp.model.entity.Product
import co.edu.unab.mgads.lpacheco.storeapp.model.repository.ProductRepository

class ProductDetailActivityViewModel(application: Application): AndroidViewModel(application) {

    private val productRepository:ProductRepository = ProductRepository(application)

    var product: Product = Product(name = "", price = 0)

    fun getProductByKey(myProductId: Int) {
        // product = productRepository.getByKeyLocal(myProductId)
    }

}