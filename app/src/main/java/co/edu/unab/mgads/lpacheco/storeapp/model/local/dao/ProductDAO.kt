package co.edu.unab.mgads.lpacheco.storeapp.model.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import co.edu.unab.mgads.lpacheco.storeapp.model.entity.Product

@Dao
interface ProductDAO {

    // fun getAll():LiveData<List<Product>>
    @Query("select * from products")
    fun getAll():LiveData<List<Product>>

    @Query("select * from products where `key` = :keyValue")
    fun getBykey(keyValue:Int):LiveData<Product>

    @Insert
    fun add(MyProduct:Product)

    @Update
    fun update(MyProduct:Product)

    @Delete
    fun delete(MyProduct:Product)



}