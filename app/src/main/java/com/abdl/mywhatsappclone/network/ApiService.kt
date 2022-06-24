package com.abdl.mywhatsappclone.network

import com.abdl.mywhatsappclone.model.CallResponse
import com.abdl.mywhatsappclone.model.ChatResponse
import com.abdl.mywhatsappclone.model.StatusResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("chat")
    suspend fun getChat(): Response<ChatResponse>

    @GET("status")
    suspend fun getStatus(): Response<StatusResponse>

    @GET("call")
    suspend fun getCall(): Response<CallResponse>
}