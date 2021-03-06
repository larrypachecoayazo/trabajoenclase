package co.edu.unab.mgads.lpacheco.storeapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import co.edu.unab.mgads.lpacheco.storeapp.model.entity.User
import co.edu.unab.mgads.lpacheco.storeapp.model.repository.UserRepository

class MainActivityViewModel: ViewModel() {

    val user: User = User(id="", name = "", password = "")
    var password:String = ""
    private val userRepository = UserRepository()

    fun login():LiveData<User?>{
        // return user.name == "larpax@gmail.com" && user.password == "123456789"
        println("Datos: ${user.email} - $password")
        return userRepository.login(user.email, password)
    }

}