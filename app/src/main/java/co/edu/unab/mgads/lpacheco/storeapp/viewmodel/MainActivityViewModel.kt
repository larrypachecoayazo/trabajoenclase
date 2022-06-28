package co.edu.unab.mgads.lpacheco.storeapp.viewmodel

import androidx.lifecycle.ViewModel
import co.edu.unab.mgads.lpacheco.storeapp.model.entity.User

class MainActivityViewModel: ViewModel() {

    val user: User = User(name = "", password = "")

    fun login():Boolean{
        return user.name == "larpax@gmail.com" && user.password == "123456789"
    }

}