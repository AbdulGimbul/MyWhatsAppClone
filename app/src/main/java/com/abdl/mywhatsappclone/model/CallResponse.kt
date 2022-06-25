package com.abdl.mywhatsappclone.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class CallResponse(

    @field:SerializedName("calls")
    val calls: List<CallsItem>
)

@Entity(tableName = "tbl_call")
data class CallsItem(

    @PrimaryKey
    @field:SerializedName("nama_kontak")
    val namaKontak: String,

    @field:SerializedName("created_at")
    val createdAt: String,

    @field:SerializedName("jenis_panggilan")
    val jenisPanggilan: Int,

    @field:SerializedName("avatar")
    val avatar: String,

    @field:SerializedName("id_call")
    val idCall: Int
)
