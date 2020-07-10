package com.daro.cleanarchitecture.posts.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.daro.cleanarchitecture.R
import com.daro.cleanarchitecture.databinding.FragmentAddBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddFragment : Fragment(R.layout.fragment_add) {
    private var fragmentBinding: FragmentAddBinding? = null
    private val binding get() = fragmentBinding!!
    private val viewModel: AddViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding = FragmentAddBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        val title = binding.addTitle.editText
        val body = binding.addBody.editText

        title?.addTextChangedListener { viewModel.onTextChanged(title.text, body?.text) }
        body?.addTextChangedListener { viewModel.onTextChanged(title?.text, body.text) }
    }
}