package com.example.kointest

import android.os.Bundle
import android.util.Log.e
import androidx.fragment.app.Fragment
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kointest.databinding.FragmentPhotoBinding

class PhotoFragment : Fragment(R.layout.fragment_photo) {
    lateinit var binding : FragmentPhotoBinding
    val viewModel: PhotoViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(this.context, 2)

        viewModel.getPhotoList()
        viewModel.photoList.observe(viewLifecycleOwner){

            e("TAG", "onViewCreated: $it")
            val adaptor = PhotoAdapter(it.photos.photo)
            recyclerView.adapter = adaptor
        }

    }
}