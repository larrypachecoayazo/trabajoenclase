package co.edu.unab.mgads.lpacheco.storeapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import co.edu.unab.mgads.lpacheco.storeapp.R
import co.edu.unab.mgads.lpacheco.storeapp.databinding.ProductItemBinding
import co.edu.unab.mgads.lpacheco.storeapp.model.entity.Product

class ProductAdapter(
    private var products: MutableList<Product>
    ) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {


    var onItemClickListener: ((Product) -> Unit)? = null
    var onItemLongClickListener: ((Product) -> Unit)? = null


    fun refresh(myProducts: MutableList<Product>) {
        products = myProducts
        notifyDataSetChanged()
    }

    class ProductViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            myProduct: Product,
            onItemClickListener: ((Product) -> Unit)?,
            onItemLongClickListener: ((Product) -> Unit)?
        ) {

            binding.product = myProduct

            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(myProduct)
                }
            }

            binding.root.setOnLongClickListener {
                onItemLongClickListener?.let {
                    it(myProduct)
                }
                true
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

        val inflate = LayoutInflater.from(parent.context)
        val binding: ProductItemBinding = DataBindingUtil.inflate(
            inflate,
            R.layout.product_item,
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position], onItemClickListener, onItemLongClickListener)
    }

    override fun getItemCount(): Int = products.size
}