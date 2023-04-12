package com.bangkit.storyapp.di

import android.content.Context
import com.bangkit.storyapp.api.ApiConfig
import com.bangkit.storyapp.database.StoryDatabase
import com.bangkit.storyapp.repository.StoryRepository

object Injection {
    fun provideRepository(context: Context): StoryRepository {
        val database = StoryDatabase.getDatabase(context)
        val apiService = ApiConfig.getApiService()

        return StoryRepository(database, apiService)
    }
}