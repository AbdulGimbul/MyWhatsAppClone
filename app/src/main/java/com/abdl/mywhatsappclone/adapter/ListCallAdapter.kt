package com.abdl.mywhatsappclone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdl.mywhatsappclone.R
import com.abdl.mywhatsappclone.databinding.ItemListCallBinding
import com.abdl.mywhatsappclone.model.CallsItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.text.SimpleDateFormat

class ListCallAdapter : RecyclerView.Adapter<ListCallAdapter.CallViewHolder>() {
    private val listCall = ArrayList<CallsItem>()

    fun setData(data: List<CallsItem>) {
        listCall.clear()
        listCall.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallViewHolder {
        val binding =
            ItemListCallBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CallViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CallViewHolder, position: Int) {
        val call = listCall[position]

        val timeInput = call.createdAt
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val formatter = SimpleDateFormat("MMM d, H:mm a")
        val time = parser.parse(timeInput)
        val formattedTime = formatter.format(time)

        with(holder.binding) {
            contactName.text = call.namaKontak
            callTime.text = formattedTime
        }

        if (call.jenisPanggilan == 1) {
            holder.binding.typeCall.setImageResource(R.drawable.ic_baseline_videocam_24)
        }

        Glide.with(holder.binding.root)
            .load(call.avatar)
            .apply(RequestOptions().override(50, 50))
            .into(holder.binding.imgContact)
    }

    override fun getItemCount(): Int = listCall.size

    class CallViewHolder(val binding: ItemListCallBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}