package com.daro.cleanarchitecture.posts.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.daro.cleanarchitecture.R
import com.daro.cleanarchitecture.databinding.FragmentDetailsBinding
import com.daro.cleanarchitecture.posts.entities.PostViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailsFragment : Fragment(R.layout.fragment_details) {
    private var fragmentBinding: FragmentDetailsBinding? = null
    private val binding get() = fragmentBinding!!
    private val arguments: DetailsFragmentArgs by navArgs()
    private val viewModel: PostDetailsViewModel by viewModel { parametersOf(arguments) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding = FragmentDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentBinding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPost().observe(viewLifecycleOwner, Observer(this::handlePost))
    }

    private fun handlePost(post: PostViewModel) {
        binding.post = post
    }
}