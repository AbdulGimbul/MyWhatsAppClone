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
import com.abdl.mywhatsappclone.adapter.ListCallAdapter
import com.abdl.mywhatsappclone.databinding.FragmentCallsBinding
import com.abdl.mywhatsappclone.model.CallsItem
import com.abdl.mywhatsappclone.utils.Resource
import com.abdl.mywhatsappclone.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CallsFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()
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

        viewModel.calls.observe(viewLifecycleOwner) { result ->
            result.data?.let { setCall(it) }

            binding.progressBar.isVisible =
                result is Resource.Loading<*> && result.data.isNullOrEmpty()
            val error = result.error?.localizedMessage
            if (result is Resource.Error<*> && result.data.isNullOrEmpty()) {
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
            }
        }
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