package com.example.somefood.ui.productListClient

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.somefood.R
import com.example.somefood.databinding.FoodItemBinding

class ProductListClientAdapter(private val itemClicked: (item: Int) -> Unit): RecyclerView.Adapter<ProductListClientAdapter.MyViewHolder>() {

    // Приватный и неизменяемый, для большего контроля деействий в адаптере
    private val myList: MutableList<Int> = mutableListOf()

    // Сначала очищаем а потом сетим новый список
    fun set(newList: List<Int>){
        this.myList.clear()
        this.myList.addAll(newList)
        notifyDataSetChanged()
    }
    // Все действия происходят в ViewHolder, чтобы он был самостоятельный
    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = FoodItemBinding.bind(view)
        fun bind(item: Int, itemClicked: (item: Int) -> Unit)
            = with(binding) {
                textView4.text = item.toString()
                root.setOnClickListener {
                    itemClicked(item)
                }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.food_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(myList[position], itemClicked)
    }

    override fun getItemCount(): Int {
        return myList.size
    }
}