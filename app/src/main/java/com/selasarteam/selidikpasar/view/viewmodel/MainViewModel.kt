package com.selasarteam.selidikpasar.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selasarteam.selidikpasar.model.MainRepository
import com.selasarteam.selidikpasar.model.local.datastore.SessionModel
import com.selasarteam.selidikpasar.model.remote.response.MarketResponse
import kotlinx.coroutines.launch

class MainViewModel(private val repo: MainRepository) : ViewModel() {
    val list: LiveData<MarketResponse> = repo.list
    val showLoading: LiveData<Boolean> = repo.showLoading

    fun getMarketList(token: String) {
        viewModelScope.launch {
            repo.getMarketList(token)
        }
    }

    fun getSession(): LiveData<SessionModel> {
        return repo.getSession()
    }
}