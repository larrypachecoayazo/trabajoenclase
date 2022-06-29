package co.edu.unab.mgads.lpacheco.storeapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseMethod
import com.bumptech.glide.Glide

@BindingAdapter("image")
fun loadIMage(imageView: ImageView, url:String){
    Glide.with(imageView.context).load(url).into(imageView)
}

@InverseMethod("stringToInt")
fun intToString(value:Int):String{
    return value.toString()
}

@InverseMethod("intToString")
fun stringToInt(value:String):Int{
    return Integer.parseInt(value)
}