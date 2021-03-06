package com.daro.cleanarchitecture.posts.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.daro.cleanarchitecture.R
import com.daro.cleanarchitecture.databinding.FragmentPostsBinding
import com.daro.cleanarchitecture.posts.adapter.OnItemClickListener
import com.daro.cleanarchitecture.posts.adapter.PostsAdapter
import com.daro.cleanarchitecture.posts.entities.PostViewModel
import com.daro.cleanarchitecture.ui.setUpRefreshLayout
import com.daro.cleanarchitecture.ui.setupSnackbar
import com.daro.domain.entities.Error
import com.daro.domain.entities.ResultState
import org.koin.androidx.viewmodel.ext.android.stateViewModel


class PostsFragment : Fragment(R.layout.fragment_posts),
    SwipeRefreshLayout.OnRefreshListener,
    OnItemClickListener {

    private val viewModel: PostsViewModel by stateViewModel()
    private var fragmentBinding: FragmentPostsBinding? = null
    private val binding get() = fragmentBinding!!
    private val postsAdapter by lazy { PostsAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding = FragmentPostsBinding.inflate(layoutInflater)
        initUi()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentBinding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPosts().observe(viewLifecycleOwner, Observer(this::handleStates))
    }

    private fun initUi() {
        binding.postsRefreshLayout.setUpRefreshLayout(this)
        binding.postsList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = postsAdapter
        }
        binding.fab.setOnClickListener { showAddItem() }
    }

    private fun showAddItem() {
        findNavController().navigate(PostsFragmentDirections.actionPostsToAdd())
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
        binding.postsRefreshLayout.isRefreshing = false
        when (error) {
            is Error.Network -> binding.root.setupSnackbar(R.string.error_network_connection)
            is Error.NotFound -> binding.root.setupSnackbar(R.string.error_backend_not_found)
            is Error.ServiceUnavailable -> binding.root.setupSnackbar(R.string.error_backend_connection)
            is UnknownError -> binding.root.setupSnackbar(R.string.error_generic)
        }
    }

    override fun onRefresh() {
        viewModel.retrievePosts(true)
    }

    override fun onItemClicked(post: PostViewModel) {
        val directions = PostsFragmentDirections.actionPostsToDetails(post.id)
        findNavController().navigate(directions)
    }

}