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
import com.selasarteam.selidikpasar.data.local.entity.NewsEntity
import com.selasarteam.selidikpasar.databinding.ItemNewsBinding
import com.selasarteam.selidikpasar.utils.DateFormatter
import com.selasarteam.selidikpasar.view.adapter.ListNewsAdapter.ListViewHolder
import com.selasarteam.selidikpasar.view.ui.DetailNewsActivity
import com.selasarteam.selidikpasar.view.ui.DetailNewsActivity.Companion.EXTRA_DATA

class ListNewsAdapter : ListAdapter<NewsEntity, ListViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val news = getItem(position)
        holder.bind(news)
    }

    class ListViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: NewsEntity) {
            binding.apply {
                tvTitleItem.text = news.title
                tvDateItem.text = DateFormatter.formatDate(news.publishedAt)
                tvNewsItem.text = news.content
                Glide.with(itemView.context)
                    .load(news.urlToImage)
                    .apply(
                        RequestOptions
                            .placeholderOf(R.drawable.ic_loading_image)
                            .error(R.drawable.ic_broken_image)
                    )
                    .into(ivPhoto)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailNewsActivity::class.java)
                    intent.putExtra(EXTRA_DATA, news)

                    val optionsCompat: ActivityOptionsCompat =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(
                            itemView.context as Activity,
                            Pair(ivPhoto, "photo"),
                            Pair(tvTitleItem, "title"),
                            Pair(tvDateItem, "date"),
                            Pair(tvNewsItem, "news")
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
                            oldNews.content == newNews.content &&
                            oldNews.urlToImage == newNews.urlToImage

                }
            }
    }
}