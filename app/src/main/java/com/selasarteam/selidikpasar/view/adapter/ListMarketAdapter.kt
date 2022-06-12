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
import com.selasarteam.selidikpasar.model.local.datastore.MarketModel
import com.selasarteam.selidikpasar.view.adapter.ListMarketAdapter.ListViewHolder


class ListMarketAdapter : ListAdapter<MarketModel, ListViewHolder>(DIFF_CALLBACK) {
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
        fun bind(market: MarketModel) {
            binding.apply {
                (market.name ?: "-").also { tvTitleItem.text = it }
                (market.address ?: "-").also { tvAddressItem.text = it }
                Glide.with(itemView.context)
                    .load(market.photo)
                    .apply(
                        RequestOptions
                            .placeholderOf(R.drawable.ic_image_loading)
                            .error(R.drawable.ic_broken_image)
                    )
                    .into(ivPhoto)

                itemView.setOnClickListener {

                }
            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<MarketModel> =
            object : DiffUtil.ItemCallback<MarketModel>() {
                override fun areItemsTheSame(oldNews: MarketModel, newNews: MarketModel): Boolean {
                    return oldNews.name == newNews.name
                }

                override fun areContentsTheSame(oldNews: MarketModel, newNews: MarketModel): Boolean {
                    return oldNews.name == newNews.name &&
                            oldNews.address == newNews.address &&
                            oldNews.photo == newNews.photo
                }
            }
    }
}