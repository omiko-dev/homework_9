package com.example.homework_9

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_9.databinding.StoreCardBinding

class StoreCardAdapter() :
    ListAdapter<Product, StoreCardAdapter.StoreCardViewHolder>(object :
        DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return  oldItem == newItem
        }
    }
    ) {
    private var showList: MutableList<Product> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreCardViewHolder {
        return StoreCardViewHolder(
            StoreCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StoreCardViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return showList.size
    }

    inner class StoreCardViewHolder(private val binding: StoreCardBinding) :
            RecyclerView.ViewHolder(binding.root){
        fun bind() {
            binding.apply {
                img.setImageResource(showList[adapterPosition].image)
                price.text = showList[adapterPosition].price
                name.text = showList[adapterPosition].title
            }
        }
    }

    fun setData(data: MutableList<Product>){
        notifyItemRangeRemoved(0, showList.size)
        showList.clear()
        showList.addAll(data)
        notifyItemRangeInserted(0, showList.size)
    }
}