package com.bangkit.storyapp.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.bangkit.storyapp.api.ApiService
import com.bangkit.storyapp.database.StoryDatabase
import com.bangkit.storyapp.response.ListStoryItem

class StoryRepository(private val storiesDatabase: StoryDatabase, private val apiService: ApiService) {
    fun getStory(header: String): LiveData<PagingData<ListStoryItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                StoryPagingSource(apiService, header)
            }
        ).liveData
    }
}