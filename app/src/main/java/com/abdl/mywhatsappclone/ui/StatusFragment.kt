package com.abdl.mywhatsappclone.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.abdl.mywhatsappclone.adapter.ListStatusAdapter
import com.abdl.mywhatsappclone.databinding.FragmentStatusBinding
import com.abdl.mywhatsappclone.model.StoryItem
import com.abdl.mywhatsappclone.network.ApiConfig
import com.abdl.mywhatsappclone.repository.MainRepository
import com.abdl.mywhatsappclone.viewmodel.MainViewModel
import com.abdl.mywhatsappclone.viewmodel.ViewModelFactory

class StatusFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
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
        val mainRepository = MainRepository(ApiConfig.getApiService())

        viewModel =
            ViewModelProvider(this, ViewModelFactory(mainRepository)).get(MainViewModel::class.java)

        viewModel.statusList.observe(viewLifecycleOwner) {
            setStatus(it)
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

        viewModel.getAllStatus()
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