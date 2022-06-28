package co.edu.unab.mgads.lpacheco.storeapp.model.entity

class Client(
    val cart: List<Product>?= listOf(),
    name: String,
    password: String
): User(
    name,
    password
) {

    override fun login(): Boolean {
        return super.login()
    }
}