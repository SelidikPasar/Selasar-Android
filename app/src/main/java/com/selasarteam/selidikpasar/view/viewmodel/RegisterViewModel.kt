package com.selasarteam.selidikpasar.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selasarteam.selidikpasar.model.MainRepository
import com.selasarteam.selidikpasar.model.remote.response.UserResponse
import com.selasarteam.selidikpasar.utils.Event
import kotlinx.coroutines.launch

class RegisterViewModel(private val repo: MainRepository) : ViewModel() {
    val registerResponse: LiveData<UserResponse> = repo.registerResponse
    val showLoading: LiveData<Boolean> = repo.showLoading
    val showMessage: LiveData<Event<String>> = repo.showMessage

    fun postRegister(name: String, email: String, password: String) {
        viewModelScope.launch {
            repo.postRegister(name, email, password)
        }
    }
}