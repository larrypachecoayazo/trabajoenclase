package co.edu.unab.mgads.lpacheco.storeapp.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import co.edu.unab.mgads.lpacheco.storeapp.R
import co.edu.unab.mgads.lpacheco.storeapp.databinding.ActivityProductAddBinding
import co.edu.unab.mgads.lpacheco.storeapp.model.entity.Product
import co.edu.unab.mgads.lpacheco.storeapp.viewmodel.ProductAddActivityViewModel
import com.bumptech.glide.Glide
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class ProductAddActivity : AppCompatActivity() {

    lateinit var binding:ActivityProductAddBinding
    lateinit var viewModel: ProductAddActivityViewModel

    private lateinit var resultGallery: ActivityResultLauncher<Intent>
    private lateinit var resultCamera: ActivityResultLauncher<Intent>

    private  var photoUri: Uri ?= null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val myProduct:Product?= intent.getSerializableExtra("product") as Product?

        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_add)

        viewModel = ViewModelProvider(this)[ProductAddActivityViewModel::class.java]

        // binding.viewModel = viewModel

        binding.ibProductAddCamera.setOnClickListener{
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            takePictureIntent.resolveActivity(packageManager).let {
                // startActivity(takePictureIntent)
                var photoFile: File ?= null
                try {
                    photoFile = createImage()
                }catch (e:IOException){

                }
                photoFile?.let {
                    photoUri = FileProvider.getUriForFile(applicationContext, "co.edu.unab.mgads.lpacheco.storeapp.fileprovider", photoFile)
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                    resultCamera.launch(takePictureIntent)
                }
            }
        }

        resultCamera = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK){
                Glide.with(applicationContext).load(photoUri).into(binding.ivProductAddLogo)
            }
        }

        binding.ibProductLoadImage.setOnClickListener{

            val intentGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intentGallery.resolveActivity(packageManager).let {
                resultGallery.launch(intentGallery)
            }

            // startActivity(intentGallery)

        }

        resultGallery = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == RESULT_OK) run{
                photoUri = it.data!!.data!!
                Glide.with(applicationContext).load(photoUri).into(binding.ivProductAddLogo)
            }
        }

        myProduct?.let {
            binding.product = myProduct
            binding.tvProductAddTitulo.text = "Ediitar ${it.name}"
            binding.btnAddProductBtnAgregar.text = "Actualizar Producto"
            binding.btnAddProductBtnAgregar.setOnClickListener {

                viewModel.edit(binding.product as Product).observe(this){ state ->
                    if (state){
                        finish()
                    }else{
                        Toast.makeText(applicationContext, "Error al actualizar", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }?:run{
            binding.product = Product(name="", price = 0)
            binding.btnAddProductBtnAgregar.setOnClickListener {
                binding.btnAddProductBtnAgregar.isEnabled=false
                viewModel.add(binding.product as Product, photoUri).observe(this){ id ->
                    if (id!="") {
                        binding.btnAddProductBtnAgregar.isEnabled=true
                        finish()
                    }else{
                        binding.btnAddProductBtnAgregar.isEnabled=true
                        Toast.makeText(applicationContext, "Error al agregar", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun createImage(): File? {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(timeStamp, ".jpg", storageDir)
    }
}