package com.selasarteam.selidikpasar.model

import com.selasarteam.selidikpasar.service.api.ApiService
import com.selasarteam.selidikpasar.utils.SessionPreferences

class MainRepository private constructor(
    private val preferences: SessionPreferences,
    private val apiService: ApiService
) {

    companion object {
        private const val TAG = "MainRepository"

        @Volatile
        private var instance: MainRepository? = null
        fun getInstance(
            preferences: SessionPreferences,
            apiService: ApiService
        ): MainRepository =
            instance ?: synchronized(this) {
                instance ?: MainRepository(preferences, apiService)
            }.also { instance = it }
    }
}