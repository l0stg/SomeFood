package com.example.somefood.ui.orderBasket

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.somefood.R
import com.example.somefood.data.model.OrderClass
import com.example.somefood.databinding.OrderItemBinding
import com.example.somefood.databinding.OrderItemByClientBinding
import com.example.somefood.ui.orderList.OrderAdapter

class OrderBasketAdapter: RecyclerView.Adapter<OrderBasketAdapter.MyViewHolder>() {

    private val myList: MutableList<OrderClass> = mutableListOf()

    fun set(newList: List<OrderClass>){
        myList.clear()
        myList.addAll(newList)
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = OrderItemBinding.bind(itemView)
        fun bind(item: OrderClass){
            with(binding) {
                itemName.text = item.orderName
                itemPrice.text = item.integerBuy.toString()
                itemTime.text = item.timeToComplit
                if (item.orderON) {
                    root.setBackgroundColor(Color.GREEN)
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