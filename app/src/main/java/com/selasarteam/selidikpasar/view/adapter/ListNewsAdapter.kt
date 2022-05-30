package com.selasarteam.selidikpasar.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.selasarteam.selidikpasar.R
import com.selasarteam.selidikpasar.data.local.entity.NewsEntity
import com.selasarteam.selidikpasar.databinding.ItemNewsBinding
import com.selasarteam.selidikpasar.view.adapter.ListNewsAdapter.ListViewHolder

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
                tvNewsItem.text = news.content
//                tvItemPublishedDate.text = DateFormatter.formatDate(news.publishedAt)
                Glide.with(itemView.context)
                    .load(news.urlToImage)
                    .apply(
                        RequestOptions
                            .placeholderOf(R.drawable.ic_loading_image)
                            .error(R.drawable.ic_broken_image)
                    )
                    .into(ivPhoto)
            }

            itemView.setOnClickListener {
//                val intent = Intent(Intent.ACTION_VIEW)
//                intent.data = Uri.parse(news.url)
//                itemView.context.startActivity(intent)
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