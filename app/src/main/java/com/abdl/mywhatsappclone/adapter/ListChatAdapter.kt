package com.abdl.mywhatsappclone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdl.mywhatsappclone.R
import com.abdl.mywhatsappclone.databinding.ItemListChatBinding
import com.abdl.mywhatsappclone.model.ChatsItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.text.SimpleDateFormat

class ListChatAdapter : RecyclerView.Adapter<ListChatAdapter.ChatViewHolder>() {
    private val listChat = ArrayList<ChatsItem>()

    fun setData(data: List<ChatsItem>) {
        listChat.clear()
        listChat.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding =
            ItemListChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chat = listChat[position]

        val timeInput = chat.createdAt
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val formatter = SimpleDateFormat("H:mm a")
        val formattedTime = parser.parse(timeInput)?.let { formatter.format(it) }

        holder.binding.apply {
            contactName.text = chat.namaKontak
            contactChat.text = chat.isiPesan
            timeChat.text = formattedTime
        }

        if (chat.status == 1) {
            holder.binding.contactChat.apply {
                compoundDrawablePadding = 2
                setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_check_24, 0, 0, 0)
            }
        }

        Glide.with(holder.binding.root)
            .load(chat.avatar)
            .apply(RequestOptions().override(50, 50))
            .into(holder.binding.imgContact)
    }

    override fun getItemCount(): Int = listChat.size

    class ChatViewHolder(val binding: ItemListChatBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}