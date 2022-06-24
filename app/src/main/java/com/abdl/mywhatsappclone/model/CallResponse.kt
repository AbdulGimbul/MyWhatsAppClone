package com.abdl.mywhatsappclone.model

import com.google.gson.annotations.SerializedName

data class CallResponse(

    @field:SerializedName("calls")
    val calls: List<CallsItem>
)

data class CallsItem(

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
