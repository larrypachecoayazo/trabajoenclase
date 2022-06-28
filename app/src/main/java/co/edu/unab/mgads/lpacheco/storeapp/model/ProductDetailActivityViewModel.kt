package co.edu.unab.mgads.lpacheco.storeapp.model

import androidx.lifecycle.ViewModel
import co.edu.unab.mgads.lpacheco.storeapp.model.entity.Product

class ProductDetailActivityViewModel: ViewModel() {

    var product: Product = Product(name = "", price = 0)

}