package com.example.somefood.ui.productListClient

import android.graphics.Color.RED
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.somefood.R
import com.example.somefood.data.model.ProductListModel
import com.example.somefood.databinding.FoodItemBinding

sealed class Click
class AddToFavorite(val item: ProductListModel): Click()
class OpenDetail(val item: ProductListModel): Click()
class AddToBuy(val item: ProductListModel): Click()

class ProductListClientAdapter(private val ClickListener: (click: Click) -> Unit): RecyclerView.Adapter<ProductListClientAdapter.MyViewHolder>() {

    private val myList: MutableList<ProductListModel> = mutableListOf()

    fun set(newList: List<ProductListModel>){
        this.myList.clear()
        this.myList.addAll(newList)
        notifyDataSetChanged()
    }
    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = FoodItemBinding.bind(view)
        fun bind(
            item: ProductListModel,
            ClickListener: (click: Click) -> Unit
        )
            = with(binding) {
                tvName.text = item.name
                tvDescription.text = item.description
            Glide
                .with(ivFood.context)
                .load(item.image)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(ivFood)

                buttonFavorite.setOnClickListener {
                    ClickListener(AddToFavorite(item))
                }

                root.setOnClickListener {
                    ClickListener(OpenDetail(item))
                }

                buttonAddToBuy.setOnClickListener {
                    ClickListener(AddToBuy(item))
                }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.food_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(myList[position], ClickListener)
    }

    override fun getItemCount(): Int =
        myList.size
}