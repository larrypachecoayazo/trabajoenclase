package co.edu.unab.mgads.lpacheco.storeapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import co.edu.unab.mgads.lpacheco.storeapp.model.entity.User
import co.edu.unab.mgads.lpacheco.storeapp.model.repository.UserRepository

class SiginupActivityViewModel : ViewModel(){
    var user:User = User("", "", "", "")
    var password:String = ""
    private val userRepository = UserRepository()

    fun signUp(): LiveData<User?>{
        return userRepository.signUp(user, password)
    }

}