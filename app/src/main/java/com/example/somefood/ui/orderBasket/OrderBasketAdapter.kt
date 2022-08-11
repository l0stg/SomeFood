package com.example.somefood.ui.orderBasket

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.somefood.R
import com.example.somefood.data.model.Order
import com.example.somefood.databinding.OrderItemByClientBinding

class OrderBasketAdapter: RecyclerView.Adapter<OrderBasketAdapter.MyViewHolder>() {

    private val myList: MutableList<Order> = mutableListOf()

    fun set(newList: List<Order>){
        myList.clear()
        myList.addAll(newList)
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = OrderItemByClientBinding.bind(itemView)
        fun bind(item: Order){
            with(binding) {
                itemName.text = item.orderName
                itemPrice.text = item.integerBuy.toString()
                itemTime.text = item.timeToComplit
                when (item.orderON) {
                    true -> root.setBackgroundColor(Color.GREEN)
                    false -> root.setBackgroundColor(Color.WHITE)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.order_item_by_client, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(myList[position])
    }

    override fun getItemCount(): Int {
        return myList.size
    }

}