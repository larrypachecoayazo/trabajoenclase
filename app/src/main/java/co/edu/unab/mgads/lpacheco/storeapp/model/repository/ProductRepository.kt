package co.edu.unab.mgads.lpacheco.storeapp.model.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import co.edu.unab.mgads.lpacheco.storeapp.model.entity.Product
import co.edu.unab.mgads.lpacheco.storeapp.model.local.StoreAppDB
import co.edu.unab.mgads.lpacheco.storeapp.model.local.dao.ProductDAO
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProductRepository(myContext:Context) {


    private var db:StoreAppDB = StoreAppDB.getInstance(myContext)
    private val productDAO:ProductDAO = db.productDAO()

    var productsObserver:MutableLiveData<List<Product>> = MutableLiveData()
    var productObserver:MutableLiveData<Product> = MutableLiveData()

    private val PRODUCT_COLLECTION: String = "products"
    private val firestore: FirebaseFirestore = Firebase.firestore

    fun loadAllLocal(){
        productsObserver.value = productDAO.getAll()
    }
    
    fun loadAllFirestone(){
        firestore.collection(PRODUCT_COLLECTION).get().addOnSuccessListener { result ->
            val productList:ArrayList<Product> = arrayListOf()
            if (!result.isEmpty){
                for (document in result.documents){
                    val myProduct:Product?= document.toObject(Product::class.java)
                    myProduct?.let {
                        it.id = document.id
                        productList.add(it)
                    }
                }
            }
            productsObserver.value=productList
        }
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
        productObserver.value = productDAO.getBykey(key)
    }

    fun getByIdFirestone(id:String){
        firestore.collection(PRODUCT_COLLECTION).document(id).get()
            .addOnSuccessListener { result ->
                val myProduct:Product ?= result.toObject(Product::class.java)
                myProduct?.let {
                    it.id = id
                    productObserver.value = it
                }
            }
            .addOnFailureListener { exception ->
                println("Error: $exception")
            }
    }

    fun addLocal(myProduct: Product){
        productDAO.add(myProduct)
    }

    fun addFirestone(myProduct:Product){
        firestore.collection(PRODUCT_COLLECTION).add(myProduct)
    }

    fun updateLocal(myProduct: Product){
        productDAO.update(myProduct)
    }

    fun updateFirestone(myProduct:Product){
        firestore.collection(PRODUCT_COLLECTION).document(myProduct.id).set(myProduct)
    }

    fun deleteLocal(myProduct: Product){
        productDAO.delete(myProduct)
        loadAllLocal()
    }

    fun deleteFirstone(myProduct:Product){
        firestore.collection(PRODUCT_COLLECTION).document(myProduct.id).delete()
        loadAllFirestone()
    }

}