package com.example.tedmobchallenge.data
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

data class PostDTO(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
)

data class Post(
    val id: Int,
    val title: String,
    val body: String
)

class PostsRepository {
    private val client: OkHttpClient
    private val gson = Gson()

    init {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    fun getPosts(): Result<List<Post>> {
        return try {
            val request = Request.Builder()
                .url("https://jsonplaceholder.typicode.com/posts")
                .build()

            val response = client.newCall(request).execute()

            if (!response.isSuccessful) {
                Result.failure(IOException("Unexpected response code: ${response.code}"))
            } else {
                response.body?.string()?.let { responseString ->
                    val listType = object : TypeToken<List<PostDTO>>() {}.type
                    val postDTOs: List<PostDTO> = gson.fromJson(responseString, listType)

                    val posts = postDTOs.map { dto ->
                        Post(
                            id = dto.id,
                            title = dto.title,
                            body = dto.body
                        )
                    }

                    Result.success(posts)
                } ?: Result.failure(IOException("Empty response body"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}