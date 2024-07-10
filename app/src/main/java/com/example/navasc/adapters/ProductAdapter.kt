package com.example.navasc.adapters
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Filter
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.example.martke.R
//import com.example.martke.data.CartProduct
//import com.example.martke.data.Product
//
//class ProductAdapter(private val productList: List<Product>, private val listener: OnItemClickListener) :
//    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
//
//    interface OnItemClickListener {
//        fun onItemClick(product: Product)
//    }
//
//    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val nameTextView: TextView = itemView.findViewById(R.id.productTitle)
//        val priceTextView: TextView = itemView.findViewById(R.id.productPrice)
//
//        init {
//            itemView.setOnClickListener {
//                val position = adapterPosition
//                if (position != RecyclerView.NO_POSITION) {
//                    listener.onItemClick(productList[position])
//                }
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val itemView = LayoutInflater.from(parent.context)
//            .inflate(R.layout.search_cart, parent, false)
//        return ViewHolder(itemView)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val currentItem = productList[position]
//        holder.nameTextView.text = currentItem.name
//        holder.priceTextView.text = currentItem.price.toString()
//    }
//
//
//
//    override fun getItemCount() = productList.size
//
//    var onProdClick: ((ProductAdapter) -> Unit)? = null
//
//
//    override fun getFilter(): Filter {
//        return object : Filter() {
//            override fun performFiltering(constraint: CharSequence?): FilterResults {
//                val query = constraint.toString().toLowerCase().trim()
//                filteredData = if (query.isEmpty()) {
//                    originalData // If the query is empty, return the original data
//                } else {
//                    originalData.filter { (subCategoryName, _) ->
//                        subCategoryName.toLowerCase().contains(query) // Filter based on subcategory name
//                    }
//                }
//                val results = FilterResults()
//                results.values = filteredData
//                return results
//            }
//
//            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
//                filteredData = results?.values as? List<Pair<String, String>> ?: emptyList()
//                notifyDataSetChanged() // Notify RecyclerView about the data change
//            }
//        }
//    }
//
//
//}

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.navasc.R
import com.example.navasc.data.Product

class ProductAdapter(
    private var productList: List<Product>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(product: Product)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.productTitle)
        val priceTextView: TextView = itemView.findViewById(R.id.productPrice)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(productList[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_rv_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = productList[position]
        holder.nameTextView.text = currentItem.name
        holder.priceTextView.text = currentItem.price.toString()
    }

    override fun getItemCount() = productList.size

    fun setData(data: List<Product>) {
        productList = data
        notifyDataSetChanged()
    }

    fun getItem(position: Int): Product {
        return productList[position]
    }
}
