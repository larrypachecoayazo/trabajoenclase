package co.edu.unab.mgads.lpacheco.storeapp.model.repository

import android.content.Context
import co.edu.unab.mgads.lpacheco.storeapp.model.entity.Product
import co.edu.unab.mgads.lpacheco.storeapp.model.local.StoreAppDB
import co.edu.unab.mgads.lpacheco.storeapp.model.local.dao.ProductDAO

class ProductRepository(myContext:Context) {

    private var db:StoreAppDB = StoreAppDB.getInstance(myContext)
    private val productDAO:ProductDAO = db.productDAO()
    private var products:MutableList<Product> = mutableListOf()


    fun loadFakeDate(){
        productDAO.apply {
            add(Product(name = "Monitor", price = 350000))
            add(Product(name = "Teclado", price = 60000))
            add(Product(name = "Disco Duro", price = 250000))
            add(Product(name = "USB", price = 50000))
            add(Product(name = "Portatil", price = 350000))
        }
        loadAllLocal()
    }

    fun getAllLocal():MutableList<Product>{
        loadAllLocal()
        return products
    }

    fun loadAllLocal(){
        products = productDAO.getAll() as MutableList<Product>
        if (products.isEmpty()){
            loadFakeDate()
        }
    }

    fun getByKeyLocal(key:Int):Product{
        return productDAO.getbykey(key)
    }

    fun addLocal(myProduct: Product){
        productDAO.add(myProduct)
        loadAllLocal()
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