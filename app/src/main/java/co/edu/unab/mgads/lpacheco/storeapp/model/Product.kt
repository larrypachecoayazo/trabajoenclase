package co.edu.unab.mgads.lpacheco.storeapp.model

import java.io.Serializable

data class Product(
    var name: String,
    val price:Int=0,
    val urlImage:String="http://campusvirtual.unicordoba.edu.co/wp-content/uploads/2022/06/packages.png",
    val description:String="",
    val status: ProductStatus = ProductStatus.AVAILABLE
 ):Serializable {

    var discount:Int=0

    init {
        discount = price - 10000

        println("El producto ${name} vale ${price}")
        println("Valor del estado ${status.value}")
        println("Valor getValues(): " + status.geValues())
    }

    override fun toString(): String {
        return "Product(name='$name', price=$price, description='$description', discount=$discount)"
    }


}