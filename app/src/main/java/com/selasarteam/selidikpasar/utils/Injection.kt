package com.selasarteam.selidikpasar.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.selasarteam.selidikpasar.data.MainRepository
import com.selasarteam.selidikpasar.data.local.room.NewsDatabase
import com.selasarteam.selidikpasar.data.remote.service.ApiConfig

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("user")

object Injection {
    fun provideRepository(context: Context): MainRepository {
        val database = NewsDatabase.getInstance(context)
        val newsDao = database.newsDao()
        val preferences = SessionPreferences.getInstance(context.dataStore)
        val apiService = ApiConfig.getApiService()
        return MainRepository.getInstance(newsDao, preferences, apiService)
    }
}