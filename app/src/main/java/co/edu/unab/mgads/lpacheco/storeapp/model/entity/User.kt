package co.edu.unab.mgads.lpacheco.storeapp.model.entity

open class User(var name:String, var password:String) {

    open fun login():Boolean {
        return name == "larry@gmail.com" && password == "12345678"
    }

}