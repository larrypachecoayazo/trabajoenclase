package co.edu.unab.mgads.lpacheco.storeapp.view

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import co.edu.unab.mgads.lpacheco.storeapp.R
import co.edu.unab.mgads.lpacheco.storeapp.databinding.ActivitySiginupBinding
import co.edu.unab.mgads.lpacheco.storeapp.model.entity.User
import co.edu.unab.mgads.lpacheco.storeapp.viewmodel.SiginupActivityViewModel

class SiginupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySiginupBinding
    private lateinit var viewModel: SiginupActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_siginup)
        viewModel = ViewModelProvider(this)[SiginupActivityViewModel::class.java]
        binding.viewModel = viewModel

        binding.siginupBtnSigin.setOnClickListener {
            Toast.makeText(applicationContext, "Creando usuario...", Toast.LENGTH_SHORT).show()
            viewModel.signUp().observe(this){
                it?.let {
                    login(it)
                }?:run{
                    Toast.makeText(applicationContext, "Error al crear el usuario", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.siginupBtnCancel.setOnClickListener {
            finish()
        }

    }

    private fun login(it: User) {
        val preferences: SharedPreferences = getSharedPreferences("store_app.pref", MODE_PRIVATE)
        val editor:SharedPreferences.Editor = preferences.edit()
        editor.putBoolean("login", true)
        editor.apply()

        val intent = Intent(applicationContext, ProductListActivity::class.java)
        intent.apply {
            putExtra("message", "Hola")
            putExtra("data", it.email)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        startActivity(intent)
    }
}