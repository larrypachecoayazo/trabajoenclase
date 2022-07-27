package co.edu.unab.mgads.lpacheco.storeapp.model.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.edu.unab.mgads.lpacheco.storeapp.model.entity.Product
import co.edu.unab.mgads.lpacheco.storeapp.model.local.StoreAppDB
import co.edu.unab.mgads.lpacheco.storeapp.model.local.dao.ProductDAO
import co.edu.unab.mgads.lpacheco.storeapp.model.remote.StoreAppAPI
import co.edu.unab.mgads.lpacheco.storeapp.model.remote.service.ProductService
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class ProductRepository(myContext:Context) {


    private var db:StoreAppDB = StoreAppDB.getInstance(myContext)
    private val productDAO:ProductDAO = db.productDAO()

    var productsObserver:MutableLiveData<List<Product>> = MutableLiveData()
    var productObserver:MutableLiveData<Product> = MutableLiveData()

    private val PRODUCT_COLLECTION: String = "products"
    private val firestore: FirebaseFirestore = Firebase.firestore


    private val api:Retrofit = StoreAppAPI.getInstance()!!
    private val productService:ProductService = api.create(ProductService::class.java)



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

    fun loadAllAPI(){
        productService.getAll().enqueue(object : Callback<Map<String, Product>?> {

            override fun onResponse(call: Call<Map<String, Product>?>, response: Response<Map<String, Product>?>) {
                val productList: ArrayList<Product> = arrayListOf()
                response.body()?.let {
                    it.forEach { (id, product) ->
                        product.id = id
                        productList.add(product)
                    }
                }
                productsObserver.value = productList
            }

            override fun onFailure(call: Call<Map<String, Product>?>, t: Throwable) {
                println("Error ${t.message}")
            }

        })
    }

    fun listenAllFirestone(){
        firestore.collection(PRODUCT_COLLECTION).addSnapshotListener { snapShot, e ->
            snapShot?.let {
                val productList:ArrayList<Product> = arrayListOf()
                if (!snapShot.isEmpty){
                    for (document in snapShot.documents){
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

    fun getByIdAPI(id: String) {
        productService.getById(id).enqueue(object : Callback<Product> {
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                response.body()?.let {
                    it.id = id
                    productObserver.value = it
                }
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                productObserver.value = null
            }

        })
    }

    fun addLocal(myProduct: Product){
        productDAO.add(myProduct)
    }

    fun addFirestone(myProduct:Product): LiveData<String>{
        val productIdObserver: MutableLiveData<String> = MutableLiveData()
        firestore.collection(PRODUCT_COLLECTION).add(myProduct).addOnSuccessListener {
            productIdObserver.value = it.id
        }.addOnFailureListener {
            productIdObserver.value = ""
        }
        return productIdObserver
    }

    fun addAPI(myProduct: Product): MutableLiveData<String> {
        val productIdObserver: MutableLiveData<String> = MutableLiveData()
        productService.add(myProduct).enqueue(object : Callback<Map<String, String>> {
            override fun onResponse(
                call: Call<Map<String, String>>,
                response: Response<Map<String, String>>
            ) {
                response.body()?.let {
                    myProduct.id = it["name"]!!
                    productIdObserver.value = myProduct.id
                }
            }

            override fun onFailure(call: Call<Map<String, String>>, t: Throwable) {
                productIdObserver.value = ""
            }

        })
        return productIdObserver
    }

    fun updateLocal(myProduct: Product){
        productDAO.update(myProduct)
    }

    fun updateFirestone(myProduct:Product): LiveData<Boolean>{
        val stateUpdateObserver: MutableLiveData<Boolean> = MutableLiveData()
        firestore.collection(PRODUCT_COLLECTION).document(myProduct.id).set(myProduct).addOnSuccessListener {
            stateUpdateObserver.value = true
        }.addOnFailureListener {
            stateUpdateObserver.value = false
        }
        return stateUpdateObserver
    }

    fun updateAPI(myProduct: Product): MutableLiveData<Boolean> {
        val stateUpdateObserver: MutableLiveData<Boolean> = MutableLiveData()
        productService.update(myProduct.id, myProduct).enqueue(object : Callback<Product> {
            override fun onResponse(
                call: Call<Product>,
                response: Response<Product>
            ) {
                response.body()?.let {
                    stateUpdateObserver.value = true
                }
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                stateUpdateObserver.value = false
            }

        })
        return stateUpdateObserver
    }

    fun deleteLocal(myProduct: Product) {
        productDAO.delete(myProduct)
        loadAllLocal()
    }

    fun deleteFirstone(myProduct:Product): LiveData<Boolean>{
        val stateDeleteObserver: MutableLiveData<Boolean> = MutableLiveData()
        firestore.collection(PRODUCT_COLLECTION).document(myProduct.id).delete().addOnSuccessListener {
            stateDeleteObserver.value = true
            loadAllFirestone()
        }.addOnFailureListener {
            stateDeleteObserver.value = false
        }
        return stateDeleteObserver
    }

    fun deleteAPI(myProduct: Product): MutableLiveData<Boolean> {
        val stateUpdateObserver: MutableLiveData<Boolean> = MutableLiveData()
        productService.delete(myProduct.id).enqueue(object : Callback<Unit> {
            override fun onResponse(
                call: Call<Unit>,
                response: Response<Unit>
            ) {
                response.body()?.let {
                    stateUpdateObserver.value = true
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                stateUpdateObserver.value = false
            }

        })
        return stateUpdateObserver
    }

}