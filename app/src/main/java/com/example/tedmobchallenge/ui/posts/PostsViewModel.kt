package com.example.tedmobchallenge.ui.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tedmobchallenge.data.Post
import com.example.tedmobchallenge.data.PostsRepository
import com.example.tedmobchallenge.domain.GetPostUseCase
import kotlinx.coroutines.launch

class PostsViewModel:ViewModel() {
    private val repository = PostsRepository()
    private val getPostUseCase = GetPostUseCase(repository)

    sealed class UiState {
        data object Loading : UiState()
        data class Success(val posts: List<Post>) : UiState()
        data class Error(val message: String) : UiState()
    }

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState


    fun loadPosts() {
        _uiState.value = UiState.Loading

        viewModelScope.launch {
            getPostUseCase(Unit).fold(
                { posts: List<Post> ->
                    _uiState.value = UiState.Success(posts)
                },
                { error: Throwable ->
                    _uiState.value = UiState.Error(error.localizedMessage ?: "Unknown error")
                }
            )
        }
    }

}