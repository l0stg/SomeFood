package com.example.somefood.ui.productListClient

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.somefood.R
import com.example.somefood.data.model.ProductListModel
import com.example.somefood.databinding.FoodItemBinding
import com.example.somefood.ui.AddToBuy
import com.example.somefood.ui.Click
import com.example.somefood.ui.OpenDetail
import com.example.somefood.ui.ToFavorite


class ProductListClientAdapter(private val clickListener: (click: Click) -> Unit) :
    RecyclerView.Adapter<ProductListClientAdapter.MyViewHolder>() {

    private val myList: MutableList<ProductListModel> = mutableListOf()

    fun set(newList: List<ProductListModel>) {
        this.myList.clear()
        this.myList.addAll(newList)
        notifyDataSetChanged()
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = FoodItemBinding.bind(view)
        fun bind(
            item: ProductListModel,
            clickListener: (click: Click) -> Unit
        ) = with(binding) {
            Glide
                .with(ivFood.context)
                .load(item.image)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(ivFood)

            buttonFavorite.setOnClickListener {
                clickListener(ToFavorite(item))
            }

            root.setOnClickListener {
                clickListener(OpenDetail(item))
            }

            buttonAddToBuy.setOnClickListener {
                clickListener(AddToBuy(item))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.food_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(myList[position], clickListener)
    }

    override fun getItemCount(): Int =
        myList.size
}