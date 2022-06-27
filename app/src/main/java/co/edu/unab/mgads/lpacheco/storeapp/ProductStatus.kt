package co.edu.unab.mgads.lpacheco.storeapp

enum class ProductStatus(val value:Int) {

    AVAILABLE(100),
    SENT(200),
    SOLD(300);

    fun geValues():String{
        return when(this){
            AVAILABLE->"Producto disponible"
            SENT->"Producto enviado"
            SOLD->"Producto vendido"
        }
    }
}