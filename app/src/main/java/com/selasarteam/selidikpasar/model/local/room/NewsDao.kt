package com.selasarteam.selidikpasar.model.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.selasarteam.selidikpasar.model.local.entity.NewsEntity

@Dao
interface NewsDao {
    @Query("SELECT * FROM articles ORDER BY date DESC")
    fun getNews(): LiveData<List<NewsEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNews(news: List<NewsEntity>)

    @Update
    suspend fun updateNews(news: NewsEntity)

    @Query("DELETE FROM articles")
    suspend fun deleteAll()
}