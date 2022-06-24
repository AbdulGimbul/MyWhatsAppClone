package com.abdl.mywhatsappclone.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.abdl.mywhatsappclone.adapter.ListCallAdapter
import com.abdl.mywhatsappclone.databinding.FragmentCallsBinding
import com.abdl.mywhatsappclone.model.CallsItem
import com.abdl.mywhatsappclone.network.ApiConfig
import com.abdl.mywhatsappclone.repository.MainRepository
import com.abdl.mywhatsappclone.viewmodel.MainViewModel
import com.abdl.mywhatsappclone.viewmodel.ViewModelFactory

class CallsFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private var _binding: FragmentCallsBinding? = null
    private val binding get() = _binding!!
    private val adapterCall = ListCallAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCallsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainRepository = MainRepository(ApiConfig.getApiService())

        viewModel =
            ViewModelProvider(this, ViewModelFactory(mainRepository)).get(MainViewModel::class.java)

        viewModel.callList.observe(viewLifecycleOwner) {
            setCall(it)
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

        viewModel.getAllCall()
    }

    private fun setCall(mList: List<CallsItem>) {
        val listItem = ArrayList<CallsItem>()
        for (list in mList) {
            listItem.addAll(listOf(list))
        }
        with(binding.rvCalls) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapterCall.setData(listItem)
            adapter = adapterCall
        }
    }
}