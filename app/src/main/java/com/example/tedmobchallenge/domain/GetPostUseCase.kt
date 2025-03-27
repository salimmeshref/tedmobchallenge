package com.example.tedmobchallenge.domain

import com.example.tedmobchallenge.data.Post
import com.example.tedmobchallenge.data.PostsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class GetPostUseCase(
    private val repository: PostsRepository,
    coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) : SuspendableUseCase<Result<List<Post>>, Unit>(coroutineDispatcher) {

    /**
     * Implementation of the performAction method from SuspendableUseCase.
     * This method fetches posts from the repository.
     */
    override suspend fun performAction(params: Unit): Result<List<Post>> {
        return repository.getPosts()
    }
}