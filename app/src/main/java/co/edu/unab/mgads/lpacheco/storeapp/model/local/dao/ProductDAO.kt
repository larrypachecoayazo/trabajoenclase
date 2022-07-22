package co.edu.unab.mgads.lpacheco.storeapp.model.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import co.edu.unab.mgads.lpacheco.storeapp.model.entity.Product

@Dao
interface ProductDAO {


    @Query("select * from products")
    fun getAll():List<Product>

    @Query("select * from products where `key` = :keyValue")
    fun getBykey(keyValue:Int):Product

    @Insert
    fun add(MyProduct:Product)

    @Update
    fun update(MyProduct:Product)

    @Delete
    fun delete(MyProduct:Product)

}