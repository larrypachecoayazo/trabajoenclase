package co.edu.unab.mgads.lpacheco.storeapp.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import co.edu.unab.mgads.lpacheco.storeapp.model.ProductStatus
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.PropertyName
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "products")
data class Product(

    @PrimaryKey(autoGenerate = true)
    @JvmField @Exclude
    @Expose(serialize = false, deserialize = false)
    var key :Int?=null,

    @Ignore
    @JvmField @Exclude
    @Expose(serialize = false, deserialize = false)
    var id : String = "",

    var name: String="",

    var price:Int=0,

    @ColumnInfo(name = "url_image")
    @JvmField @PropertyName("url_image")
    @SerializedName("url_image")
    var urlImage:String="http://campusvirtual.unicordoba.edu.co/wp-content/uploads/2022/06/packages.png",

    var description:String="",

    var status: ProductStatus = ProductStatus.AVAILABLE

 ):Serializable {

    init {
        println("Iniciando")
    }

    @Exclude
    fun getShortInfo(): String = "$name - $$price"

    override fun toString(): String {
        return "Product(id: '$id', key: '$key', name: '$name', price: '$price', description: '$description', urlImage: '$urlImage')"
    }

}