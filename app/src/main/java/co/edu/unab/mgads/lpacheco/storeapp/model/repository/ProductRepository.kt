package co.edu.unab.mgads.lpacheco.storeapp.model.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.edu.unab.mgads.lpacheco.storeapp.model.entity.Product
import co.edu.unab.mgads.lpacheco.storeapp.model.local.StoreAppDB
import co.edu.unab.mgads.lpacheco.storeapp.model.local.dao.ProductDAO

class ProductRepository(myContext:Context) {

    private var db:StoreAppDB = StoreAppDB.getInstance(myContext)
    private val productDAO:ProductDAO = db.productDAO()

    var products:MutableLiveData<List<Product>> = MutableLiveData()
    var product:MutableLiveData<Product> = MutableLiveData()


    fun loadAllLocal(){
        products.value = productDAO.getAll()
    }


    fun loadFakeDate(){
        productDAO.apply {
            add(Product(name = "Monitor", price = 350000, urlImage = "https://infographicssolutions.cl/wp-content/uploads/2022/07/706.jpg"))
            add(Product(name = "Teclado", price = 60000))
            add(Product(name = "Disco Duro", price = 250000))
            add(Product(name = "USB", price = 50000))
            add(Product(name = "Portatil", price = 350000))
        }
    }

    fun getByKeyLocal(key:Int){
        product.value = productDAO.getBykey(key)
    }

    fun addLocal(myProduct: Product){
        productDAO.add(myProduct)
    }

    fun updateLocal(myProduct: Product){
        productDAO.update(myProduct)
    }

    fun deleteLocal(myProduct: Product){
        productDAO.delete(myProduct)
        loadAllLocal()
    }

}