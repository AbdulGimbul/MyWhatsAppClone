package com.abdl.mywhatsappclone.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abdl.mywhatsappclone.model.CallsItem
import com.abdl.mywhatsappclone.model.ChatsItem
import com.abdl.mywhatsappclone.model.StoryItem
import kotlinx.coroutines.flow.Flow

@Dao
interface WACDao {

    @Query("SELECT * FROM tbl_chat")
    fun getAllChat(): Flow<List<ChatsItem>>

    @Query("SELECT * FROM tbl_status")
    fun getAllStatus(): Flow<List<StoryItem>>

    @Query("SELECT * FROM tbl_call")
    fun getAllCall(): Flow<List<CallsItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChats(chats: List<ChatsItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStatus(storyItem: List<StoryItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCall(callsItem: List<CallsItem>)

    @Query("DELETE FROM tbl_chat")
    suspend fun deleteAllChat()

    @Query("DELETE FROM tbl_status")
    suspend fun deleteAllStatus()

    @Query("DELETE FROM tbl_call")
    suspend fun deleteCallsHistory()


}