package com.selasarteam.selidikpasar.model.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.selasarteam.selidikpasar.model.local.entity.NewsEntity

@Dao
interface NewsDao {
    @Query("SELECT * FROM news ORDER BY publishedAt DESC")
    fun getNews(): LiveData<List<NewsEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNews(news: List<NewsEntity>)

    @Update
    suspend fun updateNews(news: NewsEntity)

    @Query("DELETE FROM news")
    suspend fun deleteAll()
}