package co.edu.unab.mgads.lpacheco.storeapp

import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {

    val user:User=User(name = "", password = "")

    fun login():Boolean{
        return user.name == "larpax@gmail.com" && user.password == "123456789"
    }

}