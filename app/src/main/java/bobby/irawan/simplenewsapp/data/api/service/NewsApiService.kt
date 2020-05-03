package bobby.irawan.simplenewsapp.data.api.service

import bobby.irawan.simplenewsapp.data.api.response.NewsResponse

interface NewsApiService {

    suspend fun callNewsApi(): NewsResponse?
    suspend fun callNewsApiWithCategory(category: String): NewsResponse?

}