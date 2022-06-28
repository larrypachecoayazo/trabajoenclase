package co.edu.unab.mgads.lpacheco.storeapp.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import co.edu.unab.mgads.lpacheco.storeapp.model.entity.Product
import co.edu.unab.mgads.lpacheco.storeapp.model.local.dao.ProductDAO

@Database(entities = [Product::class], version = 1, exportSchema = true)
abstract class StoreAppDB : RoomDatabase(){

    abstract fun productDAO(): ProductDAO


    // Patron singleton

    companion object {

        private var INSTANCE: StoreAppDB? = null

        fun getInstance(myContext: Context): StoreAppDB {
            var instance = INSTANCE

            if (instance == null) {
                instance = Room.databaseBuilder(myContext,
                    StoreAppDB::class.java,
                    "storeapp.db")
                    .allowMainThreadQueries()
                    .build()
            }

            return instance
        }
    }

}