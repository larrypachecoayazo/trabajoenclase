package co.edu.unab.mgads.lpacheco.storeapp.model

import androidx.lifecycle.ViewModel

class ProductDetailActivityViewModel: ViewModel() {

    var product:Product = Product(name = "", price = 0)

}