package com.abdl.mywhatsappclone.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class StatusResponse(

	@field:SerializedName("story")
	val story: List<StoryItem>
)

@Entity(tableName = "tbl_status")
data class StoryItem(

	@PrimaryKey
	@field:SerializedName("id_status")
	val idStatus: Int,

	@field:SerializedName("img_story")
	val imgStory: String,

	@field:SerializedName("nama_kontak")
	val namaKontak: String,

	@field:SerializedName("created_at")
	val createdAt: String
)
