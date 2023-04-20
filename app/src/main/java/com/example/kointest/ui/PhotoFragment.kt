package com.example.kointest.ui

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kointest.FlickrAdaptor
import com.example.kointest.R
import com.example.kointest.databinding.FragmentPhotoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoFragment : Fragment(R.layout.fragment_photo) {
    lateinit var binding: FragmentPhotoBinding
    val viewModel: PhotoViewModel by activityViewModels()
    lateinit var adapter: FlickrAdaptor
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        viewModel.getPhotoList()
        viewModel.photoList.observe(viewLifecycleOwner) {
            adapter = FlickrAdaptor()
            adapter.submitList(it.photos.photo)
            recyclerView.adapter = adapter
        }
        setScrollForNextPage()
    }

    private fun setScrollForNextPage() {
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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

