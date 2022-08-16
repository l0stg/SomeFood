package com.example.somefood.ui.orderList

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.somefood.R
import com.example.somefood.data.model.Order
import com.example.somefood.data.model.Status
import com.example.somefood.databinding.OrderItemBinding

class OrderAdapter(private val itemInOrderClick: (item: Order) -> Unit) :
    RecyclerView.Adapter<OrderAdapter.MyViewHolder>() {

    // Приватный и неизменяемый, для большего контроля деействий в адаптере
    private val myList: MutableList<Order> = mutableListOf()

    // Сначала очищаем а потом сетим новый список
    fun set(newList: List<Order>) {
        this.myList.clear()
        this.myList.addAll(newList)
        notifyDataSetChanged()
    }

    // Все действия происходят в ViewHolder, чтобы он был самостоятельный
    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val binding = OrderItemBinding.bind(view)
        fun bind(item: Order, itemInOrderClick: (item: Order) -> Unit) = with(binding) {
            nameFoodOrder.text = item.orderName
            priceFoodOrder.text = item.integerBuy.toString()
            timeFoodOrder.text = item.timeToComplit
            
            when (item.status) {
                Status.WAIT -> {
                    buttonStatus.text = view.context.getString(R.string.inJob)
                    statusFoodOrder.text = item.status.status
                    statusFoodOrder.setTextColor(Color.GRAY)
                }
                Status.JOB -> {
                    buttonStatus.text = view.context.getString(R.string.complit)
                    statusFoodOrder.text = item.status.status
                    statusFoodOrder.setTextColor(Color.YELLOW)
                }
                Status.COMPLIT -> {
                    buttonStatus.text = view.context.getString(R.string.pickUpOrder)
                    buttonStatus.setBackgroundColor(Color.GRAY)
                    statusFoodOrder.text = item.status.status
                    statusFoodOrder.setTextColor(Color.GREEN)
                }
            }

            buttonStatus.setOnClickListener {
                itemInOrderClick(item)
            }

            Glide
                .with(imageFoodorder.context)
                .load(item.image)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(imageFoodorder)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.order_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(myList[position], itemInOrderClick)
    }

    override fun getItemCount(): Int =
        myList.size

}