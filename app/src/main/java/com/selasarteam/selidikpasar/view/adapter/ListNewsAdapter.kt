package com.selasarteam.selidikpasar.view.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.selasarteam.selidikpasar.R
import com.selasarteam.selidikpasar.databinding.ItemSummaryBinding
import com.selasarteam.selidikpasar.model.local.entity.NewsEntity
import com.selasarteam.selidikpasar.view.adapter.ListNewsAdapter.ListViewHolder
import com.selasarteam.selidikpasar.view.ui.DetailSummaryActivity
import com.selasarteam.selidikpasar.view.ui.DetailSummaryActivity.Companion.EXTRA_DATA

class ListNewsAdapter : ListAdapter<NewsEntity, ListViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemSummaryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val news = getItem(position)
        holder.bind(news)
    }

    class ListViewHolder(private val binding: ItemSummaryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: NewsEntity) {
            binding.apply {
                tvTitleItem.text = news.title
                tvDateItem.text = news.date
                tvSummaryItem.text = news.summary
                Glide.with(itemView.context)
                    .load(news.image)
                    .apply(
                        RequestOptions
                            .placeholderOf(R.drawable.ic_image_loading)
                            .error(R.drawable.ic_broken_image)
                    )
                    .into(ivPhoto)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailSummaryActivity::class.java)
                    intent.putExtra(EXTRA_DATA, news)

                    val optionsCompat: ActivityOptionsCompat =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(
                            itemView.context as Activity,
                            Pair(ivPhoto, "photo"),
                            Pair(tvTitleItem, "title"),
                            Pair(tvDateItem, "date"),
                            Pair(tvSummaryItem, "articles")
                        )
                    itemView.context.startActivity(intent, optionsCompat.toBundle())
                }
            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<NewsEntity> =
            object : DiffUtil.ItemCallback<NewsEntity>() {
                override fun areItemsTheSame(oldNews: NewsEntity, newNews: NewsEntity): Boolean {
                    return oldNews.title == newNews.title
                }

                override fun areContentsTheSame(oldNews: NewsEntity, newNews: NewsEntity): Boolean {
                    return oldNews.title == newNews.title &&
                            oldNews.date == newNews.date &&
                            oldNews.summary == newNews.summary &&
                            oldNews.image == newNews.image

                }
            }
    }
}