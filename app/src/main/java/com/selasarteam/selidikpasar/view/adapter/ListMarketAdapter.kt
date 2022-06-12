package com.selasarteam.selidikpasar.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.selasarteam.selidikpasar.R
import com.selasarteam.selidikpasar.databinding.ItemMarketBinding
import com.selasarteam.selidikpasar.model.remote.response.MarketItem
import com.selasarteam.selidikpasar.view.adapter.ListMarketAdapter.ListViewHolder


class ListMarketAdapter : ListAdapter<MarketItem, ListViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemMarketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val news = getItem(position)
        holder.bind(news)
    }

    class ListViewHolder(private val binding: ItemMarketBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(market: MarketItem) {
            binding.apply {
                tvTitleItem.text = market.name
                tvAddressItem.text = market.address
                Glide.with(itemView.context)
                    .load(market.image)
                    .centerCrop()
                    .apply(
                        RequestOptions
                            .placeholderOf(R.drawable.ic_image_loading)
                            .error(R.drawable.ic_broken_image)
                    )
                    .into(ivPhoto)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<MarketItem> =
            object : DiffUtil.ItemCallback<MarketItem>() {
                override fun areItemsTheSame(
                    oldNews: MarketItem,
                    newNews: MarketItem
                ): Boolean {
                    return oldNews.name == newNews.name
                }

                override fun areContentsTheSame(
                    oldNews: MarketItem,
                    newNews: MarketItem
                ): Boolean {
                    return oldNews.name == newNews.name &&
                            oldNews.address == newNews.address &&
                            oldNews.image == newNews.image
                }
            }
    }
}