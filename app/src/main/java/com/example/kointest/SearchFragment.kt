package com.example.kointest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kointest.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import org.w3c.dom.Text

@AndroidEntryPoint
class SearchFragment : Fragment() {
    val viewModel: PhotoViewModel by activityViewModels()
    private var _binding : FragmentSearchBinding? = null
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

        binding.recyclerViewSearch.layoutManager = GridLayoutManager(this.context, 2)
        binding.button.setOnClickListener {
            viewModel.searchPhotoList( binding.editText.text.toString())
        }
        viewModel.searchList.observe(viewLifecycleOwner){
            val adaptor = PagingAdaptor()
            adaptor.submitList(it.photos.photo)
            binding.recyclerViewSearch.adapter = adaptor
        }
    }
}