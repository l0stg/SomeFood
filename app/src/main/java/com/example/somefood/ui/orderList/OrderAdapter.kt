package com.example.somefood.ui.orderList

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.somefood.R
import com.example.somefood.data.model.Order
import com.example.somefood.databinding.OrderItemBinding

class OrderAdapter(private val itemInOrderClick: (item: Order)-> Unit): RecyclerView.Adapter<OrderAdapter.MyViewHolder>() {

    // Приватный и неизменяемый, для большего контроля деействий в адаптере
    private val myList: MutableList<Order> = mutableListOf()

    // Сначала очищаем а потом сетим новый список
    fun set(newList: List<Order>){
        this.myList.clear()
        this.myList.addAll(newList)
        notifyDataSetChanged()
    }

    // Все действия происходят в ViewHolder, чтобы он был самостоятельный
    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = OrderItemBinding.bind(view)
        fun bind(item: Order, itemInOrderClick: (item: Order) -> Unit)
                = with(binding) {
            itemName.text = item.orderName
            itemPrice.text = item.integerBuy.toString()
            itemTime.text = item.timeToComplit
            when (item.orderON){
                true -> {
                    root.setBackgroundColor(Color.GREEN)
                    buttonInOrder.setBackgroundColor(Color.GRAY)
                    }
                false -> {
                    root.setBackgroundColor(Color.WHITE)
                    buttonInOrder.setBackgroundColor(Color.GREEN)
                }
            }
            buttonInOrder.setOnClickListener {
                itemInOrderClick(item)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.order_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(myList[position], itemInOrderClick)
    }

    override fun getItemCount(): Int {
        return myList.size
    }
}