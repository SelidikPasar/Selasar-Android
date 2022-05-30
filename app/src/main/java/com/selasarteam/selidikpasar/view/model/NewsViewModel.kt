package com.selasarteam.selidikpasar.view.model

import androidx.lifecycle.ViewModel
import com.selasarteam.selidikpasar.data.MainRepository

class NewsViewModel(private val repo: MainRepository) : ViewModel() {
    fun getHeadlineNews() = repo.getHeadlineNews()
}