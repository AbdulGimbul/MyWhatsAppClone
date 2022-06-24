package com.abdl.mywhatsappclone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdl.mywhatsappclone.databinding.ItemListStatusBinding
import com.abdl.mywhatsappclone.model.StoryItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class ListStatusAdapter : RecyclerView.Adapter<ListStatusAdapter.StatusViewHolder>() {
    private val listStatus = ArrayList<StoryItem>()

    fun setData(data: List<StoryItem>) {
        listStatus.clear()
        listStatus.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatusViewHolder {
        val binding =
            ItemListStatusBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StatusViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StatusViewHolder, position: Int) {
        val status = listStatus[position]

        val timeInput = status.createdAt
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val formatter = SimpleDateFormat("H:mm a")
        val past = parser.parse(timeInput)
        val now = Date()
        val time = now.time - past.time
        val formattedTime = "${TimeUnit.MILLISECONDS.toHours(time)} hours ago"

        holder.binding.contactName.text = status.namaKontak
        holder.binding.storyTime.text = formattedTime

        Glide.with(holder.binding.root)
            .load(status.imgStory)
            .apply(RequestOptions().override(50, 50))
            .into(holder.binding.imgContact)
    }

    override fun getItemCount(): Int = listStatus.size

    class StatusViewHolder(val binding: ItemListStatusBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}