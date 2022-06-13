package com.selasarteam.selidikpasar.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selasarteam.selidikpasar.model.MainRepository
import com.selasarteam.selidikpasar.model.remote.response.MarketResponse
import com.selasarteam.selidikpasar.model.remote.response.PriceResponse
import kotlinx.coroutines.launch

class DetailPriceViewModel(private val repo: MainRepository) : ViewModel()  {
    val listPrice: LiveData<PriceResponse> = repo.listPrice
    val showLoading: LiveData<Boolean> = repo.showLoading

    fun getPriceList() {
        viewModelScope.launch {
            repo.getPriceList()
        }
    }
}