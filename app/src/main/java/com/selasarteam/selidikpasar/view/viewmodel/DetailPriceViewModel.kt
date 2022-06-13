package com.selasarteam.selidikpasar.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selasarteam.selidikpasar.model.MainRepository
import kotlinx.coroutines.launch

class DetailPriceViewModel(private val repo: MainRepository) : ViewModel()  {
    fun getPriceList() {
        viewModelScope.launch {
            repo.getPriceList()
        }
    }
}