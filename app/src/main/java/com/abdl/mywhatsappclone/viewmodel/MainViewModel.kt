package com.abdl.mywhatsappclone.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.abdl.mywhatsappclone.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    mainRepository: MainRepository
) : ViewModel() {

    val chats = mainRepository.getAllChats().asLiveData()

    val status = mainRepository.getAllStatus().asLiveData()

    val calls = mainRepository.getAllCalls().asLiveData()

}