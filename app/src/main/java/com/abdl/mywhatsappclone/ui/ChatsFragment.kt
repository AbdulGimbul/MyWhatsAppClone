package com.abdl.mywhatsappclone.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.abdl.mywhatsappclone.adapter.ListChatAdapter
import com.abdl.mywhatsappclone.databinding.FragmentChatsBinding
import com.abdl.mywhatsappclone.model.ChatsItem
import com.abdl.mywhatsappclone.network.ApiConfig
import com.abdl.mywhatsappclone.repository.MainRepository
import com.abdl.mywhatsappclone.viewmodel.MainViewModel
import com.abdl.mywhatsappclone.viewmodel.ViewModelFactory

class ChatFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private var _binding: FragmentChatsBinding? = null
    private val binding get() = _binding!!
    private val adapterList = ListChatAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentChatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainRepository = MainRepository(ApiConfig.getApiService())

        viewModel =
            ViewModelProvider(this, ViewModelFactory(mainRepository)).get(MainViewModel::class.java)

        viewModel.chatList.observe(viewLifecycleOwner) {
            setChat(it)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }

        viewModel.getAllChat()
    }

    private fun setChat(mList: List<ChatsItem>) {
        val listChat = ArrayList<ChatsItem>()
        for (list in mList) {
            listChat.addAll(listOf(list))
        }
        binding.rvChat.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapterList.setData(listChat)
            adapter = adapterList

        }
    }
}