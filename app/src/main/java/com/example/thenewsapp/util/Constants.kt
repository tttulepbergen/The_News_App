package com.example.thenewsapp.util

class Constants {
    companion object{
        //пуеы the key from the BuildConfig generated during the build,not from the open source code.
        const val API_KEY = BuildConfig.NEWS_API_KEY

        const val BASE_URL = "https://newsapi.org/"
        const val SEARCH_NEWS_TIME_DELAY = 500L
        const val QUERY_PAGE_SIZE = 20
    }
}