package com.selasarteam.selidikpasar.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.selasarteam.selidikpasar.databinding.ItemPriceHeaderBinding
import com.selasarteam.selidikpasar.model.remote.response.RegionalPricesItem

class ListPriceAdapter : RecyclerView.Adapter<ListPriceAdapter.ViewHolder>() {
    private val recycledViewPool = RecyclerView.RecycledViewPool()
    private val list: ArrayList<RegionalPricesItem> = ArrayList()
    private var filter: String = ""

    fun setList(list: List<RegionalPricesItem>?) {
        if (list == null) return
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun setFilter(filter: String) {
        this.filter = filter
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemPriceHeaderBinding.bind(itemView)

        fun bind(item: RegionalPricesItem) {
            with(binding) {
                binding.tvRegion.text = item.region

                val itemAdapter = ListPriceDetailAdapter()
                rvData.layoutManager = LinearLayoutManager(itemView.context)
                rvData.adapter = itemAdapter

                for (commodity in item.commodity) {
                    if (commodity.name == filter) {
                        itemAdapter.setList(commodity.priceList)
                        rvData.setRecycledViewPool(recycledViewPool)
                        break
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPriceAdapter.ViewHolder {
        val binding = ItemPriceHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ListPriceAdapter.ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}