package com.selasarteam.selidikpasar.view.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selasarteam.selidikpasar.data.MainRepository
import com.selasarteam.selidikpasar.data.local.datastore.SessionModel
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