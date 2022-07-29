package com.example.somefood.ui.orderList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.somefood.R
import com.example.somefood.data.model.OrderClass
import com.example.somefood.databinding.OrderItemBinding

class OrderAdapter(): RecyclerView.Adapter<OrderAdapter.MyViewHolder>() {

    // Приватный и неизменяемый, для большего контроля деействий в адаптере
    private val myList: MutableList<OrderClass> = mutableListOf()

    // Сначала очищаем а потом сетим новый список
    fun set(newList: List<OrderClass>){
        this.myList.clear()
        this.myList.addAll(newList)
        notifyDataSetChanged()
    }
    // Все действия происходят в ViewHolder, чтобы он был самостоятельный
    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = OrderItemBinding.bind(view)
        fun bind(item: OrderClass)
                = with(binding) {
            textView4.text = item.orderName
            textView5.text = item.nameZakazchik
            textView6.text = item.timeToComplit
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.order_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(myList[position])
    }

    override fun getItemCount(): Int {
        return myList.size
    }
}