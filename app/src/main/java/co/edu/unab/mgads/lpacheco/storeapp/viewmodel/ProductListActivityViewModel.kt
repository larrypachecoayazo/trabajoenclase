package co.edu.unab.mgads.lpacheco.storeapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.edu.unab.mgads.lpacheco.storeapp.model.entity.Product
import co.edu.unab.mgads.lpacheco.storeapp.model.repository.ProductRepository

class ProductListActivityViewModel(application: Application): AndroidViewModel(application) {


    private val productRepository:ProductRepository = ProductRepository(application)

    var products:LiveData<List<Product>> = productRepository.productsObserver

    var productVariable:LiveData<Product> = MutableLiveData()


    fun deleteProduct(myProduct:Product){
        // productRepository.deleteLocal(myProduct)
        productRepository.deleteFirstone(myProduct)
    }

    fun loadFakeDake(){
        productRepository.loadFakeDate()
    }

    fun loadProducts(){
        // productRepository.loadAllLocal()
        productRepository.loadAllFirestone()
    }



}