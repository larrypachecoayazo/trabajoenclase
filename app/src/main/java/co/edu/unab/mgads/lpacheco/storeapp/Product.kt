package co.edu.unab.mgads.lpacheco.storeapp

 data class Product(
    var name: String,
    val price:Int=0,
    val description:String="",
    val status: ProductStatus = ProductStatus.AVAILABLE ) {

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