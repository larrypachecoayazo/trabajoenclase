package co.edu.unab.mgads.lpacheco.storeapp.model.repository

import android.content.Context
import androidx.lifecycle.LiveData
import co.edu.unab.mgads.lpacheco.storeapp.model.entity.Product
import co.edu.unab.mgads.lpacheco.storeapp.model.local.StoreAppDB
import co.edu.unab.mgads.lpacheco.storeapp.model.local.dao.ProductDAO

class ProductRepository(myContext:Context) {

    private var db:StoreAppDB = StoreAppDB.getInstance(myContext)
    private val productDAO:ProductDAO = db.productDAO()

    lateinit var products:LiveData<List<Product>>

    init {
        loadAllLocal()
    }


    fun loadFakeDate(){
        productDAO.apply {
            add(Product(name = "Monitor", price = 350000))
            add(Product(name = "Teclado", price = 60000))
            add(Product(name = "Disco Duro", price = 250000))
            add(Product(name = "USB", price = 50000))
            add(Product(name = "Portatil", price = 350000))
        }
    }

//    fun getAllLocal():MutableList<Product>{
//        loadAllLocal()
//        return products
//    }

    fun loadAllLocal(){
        products = productDAO.getAll()
    }

    fun getByKeyLocal(key:Int):LiveData<Product>{
        return productDAO.getBykey(key)
    }

    fun addLocal(myProduct: Product){
        productDAO.add(myProduct)
    }

    fun updateLocal(myProduct: Product){
        productDAO.update(myProduct)
        loadAllLocal()
    }

    fun deleteLocal(myProduct: Product){
        productDAO.delete(myProduct)
        loadAllLocal()
    }

}