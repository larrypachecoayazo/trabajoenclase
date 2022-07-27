package co.edu.unab.mgads.lpacheco.storeapp.model.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class StoreAppAPI {
    companion object {
        private var instance: Retrofit? = null
        private const val url:String = "https://storeappmgads2022-b465d-default-rtdb.firebaseio.com/"

        fun getInstance(): Retrofit?{
            if (instance == null) {
                instance = Retrofit.Builder().baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return instance
        }
    }

}