package com.selasarteam.selidikpasar.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.selasarteam.selidikpasar.model.MainRepository
import com.selasarteam.selidikpasar.model.local.datastore.MarketModel

class MainViewModel(private val repo: MainRepository) : ViewModel() {
    val list: LiveData<MarketModel> = repo.list
}