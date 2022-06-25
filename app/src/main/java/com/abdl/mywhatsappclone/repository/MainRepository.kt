package com.abdl.mywhatsappclone.repository

import androidx.room.withTransaction
import com.abdl.mywhatsappclone.source.local.WACDatabase
import com.abdl.mywhatsappclone.source.network.ApiService
import com.abdl.mywhatsappclone.source.networkBoundResource
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val api: ApiService,
    private val db: WACDatabase
) {
    private val wacDao = db.getWACDao()

    fun getAllChats() = networkBoundResource(
        query = {
            wacDao.getAllChat()
        },

        fetch = {
            api.getChat().body()?.chats
        },

        saveFetchResult = { chatList ->
            db.withTransaction {
                wacDao.deleteAllChat()
                if (chatList != null) {
                    wacDao.insertChats(chatList)
                }
            }
        }
    )

    fun getAllStatus() = networkBoundResource(
        query = {
            wacDao.getAllStatus()
        },

        fetch = {
            api.getStatus().body()?.story
        },
        saveFetchResult = { statusList ->
            db.withTransaction {
                wacDao.deleteAllStatus()
                if (statusList != null) {
                    wacDao.insertStatus(statusList)
                }
            }
        }
    )

    fun getAllCalls() = networkBoundResource(
        query = {
            wacDao.getAllCall()
        },

        fetch = {
            api.getCall().body()?.calls
        },

        saveFetchResult = { callList ->
            db.withTransaction {
                wacDao.deleteCallsHistory()
                if (callList != null) {
                    wacDao.insertCall(callList)
                }
            }
        }
    )
}