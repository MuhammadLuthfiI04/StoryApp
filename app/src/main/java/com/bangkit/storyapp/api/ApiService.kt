package com.bangkit.storyapp.api

import com.bangkit.storyapp.response.ApiResponse
import com.bangkit.storyapp.response.LoginResponse
import com.bangkit.storyapp.response.StoriesResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    fun createAccount(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ) : Call<ApiResponse>

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ) : Call<LoginResponse>

    @GET("stories")
    suspend fun getStories(
        @Header("Authorization") header: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ) : StoriesResponse

    @GET("stories")
    fun getStoriesWithLocation(
        @Header("Authorization") header: String,
        @Query("location") location: Int
    ) : Call<StoriesResponse>

    @Multipart
    @POST("stories")
    fun uploadStories(
        @Header("Authorization") header: String,
        @Part file: MultipartBody.Part,
        @Part("description") description: RequestBody,
    ) : Call<ApiResponse>

    @Multipart
    @POST("stories")
    fun uploadStoriesWithLocation(
        @Header("Authorization") header: String,
        @Part file: MultipartBody.Part,
        @Part("description") description: RequestBody,
        @Part("lat") lat: Float,
        @Part("lon") lon: Float
    ) : Call<ApiResponse>
}