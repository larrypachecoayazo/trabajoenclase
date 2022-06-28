package co.edu.unab.mgads.lpacheco.storeapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("image")
fun loadIMage(imageView: ImageView, url:String){
    Glide.with(imageView.context).load(url).into(imageView)
}