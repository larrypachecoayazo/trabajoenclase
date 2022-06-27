package co.edu.unab.mgads.lpacheco.storeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import co.edu.unab.mgads.lpacheco.storeapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var viewModel:MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]


        // binding.loginTvTitle.text = "Ingresa a la aplicación"
        binding.title = "Ingresa a la aplicación"

        binding.viewModel = viewModel

        // val myClient:User = User(name = "Larry Pacheco", password = "12345678")
        // binding.user = myClient

        binding.loginBtnLogin.setOnClickListener {

            if (viewModel.login()) {
                Toast.makeText(this, "Login exitoso", Toast.LENGTH_SHORT).show()
            } else{
                Toast.makeText(this, "Error Login", Toast.LENGTH_SHORT).show()
            }

            /*
            if ((binding.user?.name == getString(R.string.email)) &&
                (binding.user?.password == getString(R.string.password))) {
                Toast.makeText(this, "Login exitoso", Toast.LENGTH_SHORT).show()
            } else{
                Toast.makeText(this, "Error Login", Toast.LENGTH_SHORT).show()
            }
            */

        }

    }
}