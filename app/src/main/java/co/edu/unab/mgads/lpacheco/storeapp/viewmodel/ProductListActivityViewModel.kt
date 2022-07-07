package co.edu.unab.mgads.lpacheco.storeapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import co.edu.unab.mgads.lpacheco.storeapp.view.ProductAdapter
import co.edu.unab.mgads.lpacheco.storeapp.model.entity.Product
import co.edu.unab.mgads.lpacheco.storeapp.model.repository.ProductRepository

class ProductListActivityViewModel(application: Application): AndroidViewModel(application) {


    private val productRepository:ProductRepository = ProductRepository(application)
    var products:LiveData<List<Product>> = productRepository.products


    fun deleteProduct(myProduct:Product){
        productRepository.deleteLocal(myProduct)
    }

    fun loadFakeDake(){
        productRepository.loadFakeDate()
    }


}