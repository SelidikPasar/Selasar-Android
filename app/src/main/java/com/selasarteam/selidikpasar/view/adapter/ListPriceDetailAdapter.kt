package com.selasarteam.selidikpasar.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.selasarteam.selidikpasar.databinding.ItemPriceDetailBinding
import com.selasarteam.selidikpasar.model.remote.response.PriceListItem

class ListPriceDetailAdapter : RecyclerView.Adapter<ListPriceDetailAdapter.ViewHolder>() {
    private val list: ArrayList<PriceListItem> = ArrayList()
    fun setList(list: List<PriceListItem>?) {
        if (list == null) return
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemPriceDetailBinding.bind(itemView)

        fun bind(item: PriceListItem) {
            with(binding) {
                tvDate.text = item.date
                tvPrice.text = item.price.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPriceDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}