package com.abdl.mywhatsappclone.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class ChatResponse(

	@field:SerializedName("chats")
	val chats: List<ChatsItem>
)

@Entity(tableName = "tbl_chat")
data class ChatsItem(

    @PrimaryKey
    @field:SerializedName("id_chat")
    val idChat: Int,

    @field:SerializedName("isi_pesan")
    val isiPesan: String,

    @field:SerializedName("nama_kontak")
    val namaKontak: String,

    @field:SerializedName("created_at")
    val createdAt: String,

    @field:SerializedName("avatar")
    val avatar: String,

    @field:SerializedName("status")
    val status: Int
)
