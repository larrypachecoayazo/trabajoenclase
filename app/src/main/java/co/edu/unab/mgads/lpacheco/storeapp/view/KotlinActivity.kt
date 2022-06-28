package co.edu.unab.mgads.lpacheco.storeapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.edu.unab.mgads.lpacheco.storeapp.R
import co.edu.unab.mgads.lpacheco.storeapp.model.Client
import co.edu.unab.mgads.lpacheco.storeapp.model.Product
import co.edu.unab.mgads.lpacheco.storeapp.model.ProductStatus
import kotlin.random.Random

class KotlinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Constantes
        var a: Int = 200
        val b: String = "300"
        val c: Int

        println("Primer Valor de A: $a")
        a = 400
        println("Segundo Valor de A: $b")

        val email: String = getString(R.string.email)
        val password: String = getString(R.string.password)

        val emailInput = "larpax@gmail.com"
        val passwordInput = "123456789"

        if (email == emailInput && password == passwordInput){
            println("Iniciando sesión")
        }else{
            println("Error de autenticación")
        }

        val typeUser: Int = 1

        when(typeUser){
            1->{ println("Administrador") }
            2->{ println("Registrado") }
            else->{ println("invitado") }
        }

        val products = mutableListOf("keyboard", "mouse", "monitor")


        for (product in products){
            println("Producto -> ${product}")
        }

        products.removeAt(1)
        println(products)



        val monitors = mutableMapOf("sony" to 20000)
        monitors["samsung"] = 500000
        monitors.put("hp", 150000)
        println(monitors)

        for (monitor in monitors){
            println("Monitor -> ${monitor.key}:${monitor.value} ")
        }
        println("Clave-Valor")
        for ((key, value ) in monitors){
            println("Monitor -> ${key}:${value} ")
        }

        // NULL Safety

        var testNull:String? = "Holaa"



        val l = testNull?.length ?: -1


        //testNull = null
        val neverNull:Int = testNull!!.length
        println("Longitud: ${neverNull}")


        // Generamos valores booleanos 10 veces. Si es flase,
        // retorna null a randomNull
        repeat(10) { count ->
            val randomNull = when (Random.nextBoolean()) {
                false -> null
                true -> "not null"
            }
            randomNull?.let {
                println("$count $it")
            }?:run{
                println("$count Este es nulo")
            }
        }

        showProducts(price=250000, name = "Computador")

        if (login(emailInput, passwordInput)){
            println("Login exitoso")
        }

        val portatil = Product("Portatil", 250000, "Portatil ASUS", status=ProductStatus.AVAILABLE)
        val portatil2 = Product("Portatil", 250000, "Portatil ASUS", status=ProductStatus.AVAILABLE)

        val equalsClass:Boolean = portatil == portatil2
        println("Igualdad: ${equalsClass}")

        val (n, p, d, s) = portatil

        val myClient: Client = Client(name="larpax@gmail.com", password = "123456789")
        // myClient.login("", "")


        println(portatil)
        portatil.name = "Teclado"
        println("Serialiada: " + portatil.toString())
        println(portatil.discount)

        var myClient2: Client = Client(name="larry@gmail.com", password = "12345678")
//        myClient2.login()




    }

    fun showProducts(name: String, price: Int): Unit {
        println("El producto ${name} tiene un precio de ${price}")
    }

    fun login(email: String, password: String) : Boolean = email == getString(R.string.email) && password == getString(
        R.string.password
    )

    fun clickListener(click:(String)->Boolean) {
        click("Hola")
    }

}