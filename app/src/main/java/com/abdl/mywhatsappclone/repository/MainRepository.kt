package com.abdl.mywhatsappclone.repository

import com.abdl.mywhatsappclone.network.ApiService

class MainRepository constructor(private val retrofitService: ApiService) {
    suspend fun getAllChats() = retrofitService.getChat()

    suspend fun getAllStatus() = retrofitService.getStatus()

    suspend fun getAllCalls() = retrofitService.getCall()
}