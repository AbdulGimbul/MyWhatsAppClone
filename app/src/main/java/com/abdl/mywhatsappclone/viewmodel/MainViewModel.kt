package com.abdl.mywhatsappclone.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abdl.mywhatsappclone.model.CallsItem
import com.abdl.mywhatsappclone.model.ChatsItem
import com.abdl.mywhatsappclone.model.StoryItem
import com.abdl.mywhatsappclone.repository.MainRepository
import kotlinx.coroutines.*

class MainViewModel constructor(private val mainRepository: MainRepository) : ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val chatList = MutableLiveData<List<ChatsItem>>()
    val statusList = MutableLiveData<List<StoryItem>>()
    val callList = MutableLiveData<List<CallsItem>>()
    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()

    fun getAllChat() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = mainRepository.getAllChats()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    chatList.postValue(response.body()?.chats)
                    loading.postValue(false)
                } else {
                    onError("Error : ${response.message()}")
                }
            }
        }
    }

    fun getAllStatus() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = mainRepository.getAllStatus()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    statusList.postValue(response.body()?.story)
                    loading.postValue(false)
                } else {
                    onError("Error : ${response.message()}")
                }
            }
        }
    }

    fun getAllCall() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = mainRepository.getAllCalls()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    callList.postValue(response.body()?.calls)
                    loading.postValue(false)
                } else {
                    onError("Error : ${response.message()}")
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.postValue(message)
        loading.postValue(false)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}