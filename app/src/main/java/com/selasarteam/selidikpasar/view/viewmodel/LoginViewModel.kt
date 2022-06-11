package com.selasarteam.selidikpasar.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selasarteam.selidikpasar.model.MainRepository
import com.selasarteam.selidikpasar.model.local.datastore.SessionModel
import kotlinx.coroutines.launch

class LoginViewModel(private val repo: MainRepository) : ViewModel() {
//    fun postLogin(email: String, password: String) {
//        viewModelScope.launch {
//            repo.postLogin(email, password)
//        }
//    }

    fun saveSession(session: SessionModel) {
        viewModelScope.launch {
            repo.saveSession(session)
        }
    }

    fun login() {
        viewModelScope.launch {
            repo.login()
        }
    }
}