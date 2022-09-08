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
import com.example.somefood.ui.ClickOrder
import com.example.somefood.ui.ItemInOrderClick
import com.example.somefood.ui.OpenDetailInfo

class OrderBasketAdapter(private val clickListener: (click: ClickOrder) -> Unit) :
    RecyclerView.Adapter<OrderBasketAdapter.MyViewHolder>() {

    private val myList: MutableList<Order> = mutableListOf()

    fun set(newList: List<Order>) {
        myList.clear()
        myList.addAll(newList)
        notifyDataSetChanged()
    }

    class MyViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = OrderItemByClientBinding.bind(itemView)
        fun bind(item: Order, clickListener: (click: ClickOrder) -> Unit) {
            with(binding) {
                nameFoodOrder.text = item.orderName
                priceFoodOrder.text = item.integerBuy.toString()
                timeFoodOrder.text = item.timeToComplete
                when (item.status) {
                    Status.COMPLETE -> {
                        buttonPickUp.visibility = View.VISIBLE
                        buttonPickUp.isEnabled = true
                        statusFoodOrder.text = itemView.context.getString(R.string.complit)
                        statusFoodOrder.setTextColor(Color.GREEN)
                    }
                    else -> {
                        buttonPickUp.visibility = View.INVISIBLE
                        buttonPickUp.isEnabled = false
                        statusFoodOrder.text = itemView.context.getString(R.string.inWait)
                        when (item.status) {
                            Status.WAIT -> statusFoodOrder.setTextColor(Color.GRAY)
                            Status.JOB -> statusFoodOrder.setTextColor(Color.YELLOW)
                            else -> {}
                        }
                    }
                }

                Glide
                    .with(imageFoodorder.context)
                    .load(item.image)
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(imageFoodorder)

                buttonPickUp.setOnClickListener {
                    clickListener(ItemInOrderClick(item))
                }

                root.setOnClickListener {
                    clickListener(OpenDetailInfo(item))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.order_item_by_client, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(myList[position], clickListener)
    }

    override fun getItemCount(): Int =
        myList.size
}