package com.example.kointest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kointest.FlickrAdaptor
import com.example.kointest.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    val viewModel: PhotoViewModel by activityViewModels()
    lateinit var adapter: FlickrAdaptor
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewSearch.layoutManager = LinearLayoutManager(this.context)

        binding.button.setOnClickListener {
            viewModel.searchPhotoList(binding.editText.text.toString())
        }
        viewModel.searchList.observe(viewLifecycleOwner) {
            val adaptor = FlickrAdaptor()
            adaptor.submitList(it.photos.photo)
            binding.recyclerViewSearch.adapter = adaptor
        }
        setScrollForNextPage()
    }

    private fun setScrollForNextPage() {
        binding.recyclerViewSearch.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastPosition = layoutManager.findLastVisibleItemPosition()
                if (lastPosition == adapter.itemCount - 1) {
                    viewModel.nexPage()
                }
            }
        })
    }
}