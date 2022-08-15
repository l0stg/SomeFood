package com.example.somefood.ui.orderBasket

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.somefood.R
import com.example.somefood.data.model.Order
import com.example.somefood.data.model.Status
import com.example.somefood.databinding.OrderItemByClientBinding

class OrderBasketAdapter(private val buttonPickUpOrder: (item: Order)-> Unit): RecyclerView.Adapter<OrderBasketAdapter.MyViewHolder>() {

    private val myList: MutableList<Order> = mutableListOf()

    fun set(newList: List<Order>){
        myList.clear()
        myList.addAll(newList)
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = OrderItemByClientBinding.bind(itemView)
        fun bind(item: Order, buttonPickUpOrder: (item: Order) -> Unit){
            with(binding) {
                nameFoodOrder.text = item.orderName
                priceFoodOrder.text = item.integerBuy.toString()
                timeFoodOrder.text = item.timeToComplit
                when (item.status){
                    Status.WAIT -> {
                        buttonPickUp.visibility = View.INVISIBLE
                        buttonPickUp.isEnabled = false
                        statusFoodOrder.text = item.status.status
                        statusFoodOrder.setTextColor(Color.GRAY)
                    }
                    Status.JOB -> {
                        buttonPickUp.visibility = View.INVISIBLE
                        buttonPickUp.isEnabled = false
                        statusFoodOrder.text = item.status.status
                        statusFoodOrder.setTextColor(Color.YELLOW)
                    }
                    Status.COMPLIT -> {
                        buttonPickUp.visibility = View.VISIBLE
                        buttonPickUp.isEnabled = true
                        statusFoodOrder.text = item.status.status
                        statusFoodOrder.setTextColor(Color.GREEN)
                    }
                }
                Glide
                    .with(imageFoodorder.context)
                    .load(item.image)
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(imageFoodorder)

                buttonPickUp.setOnClickListener{
                    buttonPickUpOrder(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.order_item_by_client, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(myList[position], buttonPickUpOrder)
    }

    override fun getItemCount(): Int =
        myList.size
}