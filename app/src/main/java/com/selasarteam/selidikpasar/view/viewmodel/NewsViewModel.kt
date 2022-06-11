package com.selasarteam.selidikpasar.view.viewmodel

import androidx.lifecycle.ViewModel
import com.selasarteam.selidikpasar.data.MainRepository

class NewsViewModel(private val repo: MainRepository) : ViewModel() {
    fun getSummaryNews() = repo.getSummaryNews()
}