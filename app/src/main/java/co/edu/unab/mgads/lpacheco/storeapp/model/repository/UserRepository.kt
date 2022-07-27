package co.edu.unab.mgads.lpacheco.storeapp.model.repository

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.edu.unab.mgads.lpacheco.storeapp.model.entity.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UserRepository {

    private val firestone = Firebase.firestore
    private val auth = Firebase.auth
    private val USER_COLLECTION:String = "users"
    private val userObserver:MutableLiveData<User?> = MutableLiveData()

    fun signUp(user:User, password:String): LiveData<User?>{
        auth.createUserWithEmailAndPassword(user.email, password)
            .addOnSuccessListener {
                user.id = auth.uid!!
                createFirestone(user)
            }
            .addOnFailureListener {
                println("Error: ${it.message}")
                userObserver.value = null
            }
            return userObserver
    }

    public fun createFirestone(user:User){
        firestone.collection(USER_COLLECTION).document(user.id).set(user)
            .addOnSuccessListener {
                userObserver.value = user
            }
            .addOnFailureListener {
                userObserver.value = null
            }
    }

    public fun login(email:String, password:String): LiveData<User?> {
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {

            getByIdFirestone(auth.uid)
        }.addOnFailureListener {
            userObserver.value = null
        }
        return userObserver
    }

    private fun getByIdFirestone(uid: String?) {
        uid?.let { id ->
            firestone.collection(USER_COLLECTION).document(id).get()
                .addOnSuccessListener { response ->
                    val user:User ?= response.toObject(User::class.java)

                    user?.let{
                        it.id = id
                        userObserver.value = user
                    }
                }
                .addOnFailureListener {
                    userObserver.value = null
                }
        }?:run {
            userObserver.value = null
        }

    }


}