package co.edu.unab.mgads.lpacheco.storeapp.view

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import co.edu.unab.mgads.lpacheco.storeapp.viewmodel.MainActivityViewModel
import co.edu.unab.mgads.lpacheco.storeapp.R
import co.edu.unab.mgads.lpacheco.storeapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // setContentView(R.layout.activity_main)



        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        val preferences = getSharedPreferences("store_app.pref", MODE_PRIVATE )

        // preferences.edit().clear().apply()
        // preferences.edit().remove("login").apply()



        if (preferences.getBoolean("login", false)){
            val intent = Intent(applicationContext, ProductListActivity::class.java)
            intent.putExtra("mensaje", "Hola")
            intent.putExtra("data", viewModel.user.name)
            startActivity(intent)
            finish()
        }


        // binding.loginTvTitle.text = "Ingresa a la aplicación"
        binding.title = "Ingresa a la aplicación"

        binding.viewModel = viewModel

        // val myClient:User = User(name = "Larry Pacheco", password = "12345678")
        // binding.user = myClient


        // Toast.makeText(this, "Error Login", Toast.LENGTH_SHORT).show()

        binding.loginBtnSignup.setOnClickListener {
            val intent = Intent(applicationContext, SiginupActivity::class.java)
            startActivity(intent)
        }


        binding.loginBtnLogin.setOnClickListener {

            viewModel.login().observe(this) {
                it?.let {
                    login()
                }?:run{
                    Toast.makeText(this, "Datos de autenticación inválidos", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun login(){
        val preferences:SharedPreferences = getSharedPreferences("store_app.pref", MODE_PRIVATE)
        var editor:SharedPreferences.Editor = preferences.edit()
        editor.putBoolean("login", true)
        editor.apply()

        val intent = Intent(applicationContext, ProductListActivity::class.java)
        intent.putExtra("mensaje", "Hola")
        intent.putExtra("data", viewModel.user.name)
        startActivity(intent)

        finish()
    }
}