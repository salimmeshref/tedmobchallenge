package com.example.tedmobchallenge.ui.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tedmobchallenge.data.Post
import com.example.tedmobchallenge.databinding.FragmentPostsBinding

class PostsFragment : Fragment() {

    private var _binding: FragmentPostsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: PostsViewModel
    private lateinit var adapter: PostsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupRecyclerView()
        observeViewModel()
        viewModel.loadPosts()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(PostsViewModel::class.java)
    }

    private fun setupRecyclerView() {
        adapter = PostsAdapter()
        binding.rvPosts.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPosts.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is PostsViewModel.UiState.Loading -> showLoading()
                is PostsViewModel.UiState.Success -> showPosts(state.posts)
                is PostsViewModel.UiState.Error -> showError(state.message)
            }
        }
    }

    private fun showPosts(posts: List<Post>) {
        binding.progressBar.visibility = View.GONE
        binding.error.visibility = View.GONE
        binding.rvPosts.visibility = View.VISIBLE
        adapter.list = posts
    }

    private fun showError(message: String) {
        binding.progressBar.visibility = View.GONE
        binding.rvPosts.visibility = View.GONE
        binding.error.visibility = View.VISIBLE
        binding.error.text = message
    }

    private fun showLoading(){
        binding.progressBar.visibility = View.VISIBLE
        binding.rvPosts.visibility = View.GONE
        binding.error.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}