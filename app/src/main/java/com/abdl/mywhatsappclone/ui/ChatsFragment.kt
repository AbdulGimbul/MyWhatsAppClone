package com.abdl.mywhatsappclone.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.abdl.mywhatsappclone.adapter.ListChatAdapter
import com.abdl.mywhatsappclone.databinding.FragmentChatsBinding
import com.abdl.mywhatsappclone.model.ChatsItem
import com.abdl.mywhatsappclone.utils.Resource
import com.abdl.mywhatsappclone.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatsFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()
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

        viewModel.chats.observe(viewLifecycleOwner) { result ->
            result.data?.let { data -> setChat(data) }

            binding.progressBar.isVisible =
                result is Resource.Loading<*> && result.data.isNullOrEmpty()
            val error = result.error?.localizedMessage
            if (result is Resource.Error<*> && result.data.isNullOrEmpty()) {
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
            }
        }

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