package com.daro.cleanarchitecture.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.daro.cleanarchitecture.databinding.ActivityMainBinding
import com.daro.cleanarchitecture.main.adapter.PostsAdapter
import com.daro.cleanarchitecture.main.entities.PostViewModel
import com.daro.domain.entities.Error
import com.daro.domain.entities.ResultState
import org.koin.androidx.viewmodel.ext.android.stateViewModel

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    private val viewModel: MainViewModel by stateViewModel()
    private lateinit var binding: ActivityMainBinding
    private val postsAdapter by lazy { PostsAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initUi()
        viewModel.getPosts().observe(this, Observer(this::handleStates))
    }

    private fun initUi() {
        binding.postsRefreshLayout.apply {
            setOnRefreshListener(this@MainActivity)
        }
        binding.postsList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = postsAdapter
        }
    }

    private fun handleStates(state: ResultState<List<PostViewModel>>) {
        when (state) {
            is ResultState.LoadingState -> binding.postsRefreshLayout.isRefreshing = true
            is ResultState.ErrorState -> handleErrorState(state.error)
            is ResultState.SuccessState -> {
                binding.postsRefreshLayout.isRefreshing = false
                postsAdapter.submitList(state.data)
            }
        }
    }

    private fun handleErrorState(error: Error) {
        when (error) {
            is Error.Network -> {
            }
            is Error.NotFound -> {
            }
            is Error.ServiceUnavailable -> {
            }
            is UnknownError -> {
            }
        }
    }

    override fun onRefresh() {
        viewModel.retrievePosts(true)
    }
}