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
import com.abdl.mywhatsappclone.adapter.ListStatusAdapter
import com.abdl.mywhatsappclone.databinding.FragmentStatusBinding
import com.abdl.mywhatsappclone.model.StoryItem
import com.abdl.mywhatsappclone.utils.Resource
import com.abdl.mywhatsappclone.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StatusFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()
    private var _binding: FragmentStatusBinding? = null
    private val binding get() = _binding!!
    private val adapterStatus = ListStatusAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentStatusBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.status.observe(viewLifecycleOwner) { result ->
            result.data?.let { setStatus(it) }

            binding.progressBar.isVisible =
                result is Resource.Loading<*> && result.data.isNullOrEmpty()
            val error = result.error?.localizedMessage
            if (result is Resource.Error<*> && result.data.isNullOrEmpty()) {
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun setStatus(mList: List<StoryItem>) {
        val listStory = ArrayList<StoryItem>()
        for (list in mList) {
            listStory.addAll(listOf(list))
        }
        with(binding.rvStatus) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapterStatus.setData(listStory)
            adapter = adapterStatus
        }
    }

}