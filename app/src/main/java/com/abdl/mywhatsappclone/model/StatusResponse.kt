package com.abdl.mywhatsappclone.model

import com.google.gson.annotations.SerializedName

data class StatusResponse(

	@field:SerializedName("story")
	val story: List<StoryItem>
)

data class StoryItem(

	@field:SerializedName("id_status")
	val idStatus: Int,

	@field:SerializedName("img_story")
	val imgStory: String,

	@field:SerializedName("nama_kontak")
	val namaKontak: String,

	@field:SerializedName("created_at")
	val createdAt: String
)
