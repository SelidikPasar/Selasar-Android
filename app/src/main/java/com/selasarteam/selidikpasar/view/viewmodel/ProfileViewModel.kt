package com.selasarteam.selidikpasar.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selasarteam.selidikpasar.model.MainRepository
import com.selasarteam.selidikpasar.model.local.datastore.SessionModel
import kotlinx.coroutines.launch

class ProfileViewModel(private val repo: MainRepository) : ViewModel()  {

    fun getSession(): LiveData<SessionModel> {
        return repo.getSession()
    }

    fun logout() {
        viewModelScope.launch {
            repo.logout()
        }
    }
}