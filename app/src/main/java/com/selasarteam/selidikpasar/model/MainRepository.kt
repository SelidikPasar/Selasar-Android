package com.selasarteam.selidikpasar.model

import android.util.Log
import androidx.lifecycle.*
import com.selasarteam.selidikpasar.model.local.datastore.MarketModel
import com.selasarteam.selidikpasar.model.local.datastore.SessionModel
import com.selasarteam.selidikpasar.model.local.entity.NewsEntity
import com.selasarteam.selidikpasar.model.local.room.NewsDao
import com.selasarteam.selidikpasar.model.remote.service.ApiService
import com.selasarteam.selidikpasar.utils.Result
import com.selasarteam.selidikpasar.model.local.datastore.SessionPreferences
import com.selasarteam.selidikpasar.model.remote.response.UserResponse
import com.selasarteam.selidikpasar.utils.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository private constructor(
    private val newsDao: NewsDao,
    private val preferences: SessionPreferences,
    private val apiService: ApiService
) {
    private val _list = MutableLiveData<MarketModel>()
    val list: LiveData<MarketModel> = _list

    private val _registerResponse = MutableLiveData<UserResponse>()
    val registerResponse: LiveData<UserResponse> = _registerResponse

    private val _loginResponse = MutableLiveData<UserResponse>()
    val loginResponse: LiveData<UserResponse> = _loginResponse

    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading: LiveData<Boolean> = _showLoading

    private val _showMessage = MutableLiveData<Event<String>>()
    val showMessage: LiveData<Event<String>> = _showMessage

    fun getSummaryNews(): LiveData<Result<List<NewsEntity>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getSummaryNews()
            val articles = response.articles
            val newsList = articles.map { article ->
                NewsEntity(
                    article.title,
                    article.summary ?: "None",
                    article.source ?: "Anonymous",
                    article.date,
                    article.predictedSummary ?: "None",
                    article.image,
                    article.url
                )
            }
            newsDao.deleteAll()
            newsDao.insertNews(newsList)
        } catch (e: Exception) {
            Log.d(TAG, "getSummaryNews: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
        val localData: LiveData<Result<List<NewsEntity>>> =
            newsDao.getNews().map { Result.Success(it) }
        emitSource(localData)
    }

    fun postRegister(name: String, email: String, password: String) {
        _showLoading.value = true
        val client = apiService.postRegister(name, email, password)

        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                val responseBody = response.body()
                val message = responseBody?.message

                _showLoading.value = false
                if (response.isSuccessful) {
                    _registerResponse.value = responseBody
                    _showMessage.value = Event("${response.message()}, $message")
                } else {
                    _showMessage.value = Event("${response.message()}, $message")
                    Log.e(TAG, "onFailure: ${response.message()}, $message")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                _showLoading.value = false
                _showMessage.value = Event(t.message.toString())
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun postLogin(email: String, password: String) {
        _showLoading.value = true
        val client = apiService.postLogin(email, password)

        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                val responseBody = response.body()
                val message = responseBody?.message

                _showLoading.value = false
                if (response.isSuccessful) {
                    _loginResponse.value = responseBody
                    _showMessage.value = Event("${response.message()}, $message")
                } else {
                    _showMessage.value = Event("${response.message()}, $message")
                    Log.e(TAG, "onFailure: ${response.message()}, $message")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                _showLoading.value = false
                _showMessage.value = Event(t.message.toString())
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getSession(): LiveData<SessionModel> {
        return preferences.getSession().asLiveData()
    }

    suspend fun saveSession(session: SessionModel) {
        preferences.saveSession(session)
    }

    suspend fun login() {
        preferences.login()
    }

    suspend fun logout() {
        preferences.logout()
    }

    companion object {
        private const val TAG = "MainRepository"

        @Volatile
        private var instance: MainRepository? = null
        fun getInstance(
            newsDao: NewsDao,
            preferences: SessionPreferences,
            apiService: ApiService
        ): MainRepository =
            instance ?: synchronized(this) {
                instance ?: MainRepository(newsDao, preferences, apiService)
            }.also { instance = it }
    }
}