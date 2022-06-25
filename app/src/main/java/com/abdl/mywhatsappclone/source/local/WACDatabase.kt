package com.abdl.mywhatsappclone.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abdl.mywhatsappclone.model.CallsItem
import com.abdl.mywhatsappclone.model.ChatsItem
import com.abdl.mywhatsappclone.model.StoryItem

@Database(
    entities = [ChatsItem::class, StoryItem::class, CallsItem::class],
    version = 1
)
abstract class WACDatabase : RoomDatabase() {
    abstract fun getWACDao(): WACDao

}