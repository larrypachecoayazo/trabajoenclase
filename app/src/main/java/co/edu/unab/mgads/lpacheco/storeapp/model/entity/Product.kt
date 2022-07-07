package co.edu.unab.mgads.lpacheco.storeapp.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import co.edu.unab.mgads.lpacheco.storeapp.model.ProductStatus
import java.io.Serializable

@Entity(tableName = "products")
data class Product(

    @PrimaryKey(autoGenerate = true)
    var key :Int?=null,

    var name: String,

    var price:Int=0,

    @ColumnInfo(name = "url_image")
    var urlImage:String="http://campusvirtual.unicordoba.edu.co/wp-content/uploads/2022/06/packages.png",

    var description:String="",

    var status: ProductStatus = ProductStatus.AVAILABLE

 ):Serializable {

    /*init {
        discount = price - 10000

        println("El producto ${name} vale ${price}")
        println("Valor del estado ${status.value}")
        println("Valor getValues(): " + status.geValues())
    }*/

    override fun toString(): String {
        return "Product(key: '$key', name: '$name', price: '$price', description: '$description', url: '$urlImage')"
    }


}