package com.example.somefood.ui.FavoriteFood

import android.graphics.Color.RED
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.somefood.R
import com.example.somefood.data.model.FoodDataBaseModel
import com.example.somefood.databinding.FoodItemBinding

class FavoriteAdapter(private val itemDelete: (item: Int) -> Unit): RecyclerView.Adapter<FavoriteAdapter.MyViewHolder>() {

    // Приватный и неизменяемый, для большего контроля деействий в адаптере
    private val myList: MutableList<FoodDataBaseModel> = mutableListOf()

    // Сначала очищаем а потом сетим новый список
    fun set(newList: List<FoodDataBaseModel>){
        this.myList.clear()
        this.myList.addAll(newList)
        notifyDataSetChanged()
    }
    // Все действия происходят в ViewHolder, чтобы он был самостоятельный
    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = FoodItemBinding.bind(view)
        fun bind(item: FoodDataBaseModel, itemDelete: (item: Int) -> Unit)
                = with(binding) {
            tvName.text = item.name
            tvDescription.text = item.description
            Glide
                .with(ivFood.context)
                .load(item.image)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(ivFood)
            buttonFavorite.setBackgroundColor(RED)
            buttonFavorite.setOnClickListener {
                item.id?.let { itemDelete(it) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.food_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(myList[position], itemDelete)
    }

    override fun getItemCount(): Int {
        return myList.size
    }
}