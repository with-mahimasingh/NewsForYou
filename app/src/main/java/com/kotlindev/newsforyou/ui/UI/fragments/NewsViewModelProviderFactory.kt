package com.kotlindev.newsforyou.ui.UI.fragments

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kotlindev.newsforyou.ui.NewsApplication
import com.kotlindev.newsforyou.ui.repository.NewsRepository

class NewsViewModelProviderFactory(
    val app: Application,
    val newsRepository: NewsRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(app as NewsApplication, newsRepository) as T
    }
}