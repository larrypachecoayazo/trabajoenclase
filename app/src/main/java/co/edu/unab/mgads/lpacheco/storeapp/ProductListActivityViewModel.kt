package co.edu.unab.mgads.lpacheco.storeapp

import androidx.lifecycle.ViewModel

class ProductListActivityViewModel: ViewModel() {

    private val products: MutableList<Product> = arrayListOf()
    var adapter:ProductAdapter = ProductAdapter(products)

    fun loadProducts(){
        products.apply {
            clear()
            add(Product(name = "Monitor", price = 350000))
            add(Product(name = "Teclado", price = 60000))
            add(Product(name = "Disco Duro", price = 250000))
            add(Product(name = "USB", price = 50000))
            add(Product(name = "Portatil", price = 350000))
        }
    }

    fun refreshData(){
        adapter.refresh(products)
    }
}