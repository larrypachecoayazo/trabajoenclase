package co.edu.unab.mgads.lpacheco.storeapp.model.entity

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.PropertyName

open class User(
    @JvmField @Exclude
    var id: String = "",
    var document: String = "",
    var name: String = "",
    var email: String = "",
    @JvmField @PropertyName("url_photo")
    var urlPhoto: String = "https://www.citypng.com/public/uploads/small/11639594314mvt074h0zt5cijvfdn7gqxbrya72bzqulyd5bhqhemb5iasfe7gbydmr2jxk8lcclcp6qrgaoiuiavf4sendwc3jvwadddqmli2d.png",
    var password: String = ""
) {


}