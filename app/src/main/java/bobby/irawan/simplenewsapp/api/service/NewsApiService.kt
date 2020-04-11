package bobby.irawan.simplenewsapp.api.service

import bobby.irawan.simplenewsapp.api.response.NewsResponse

interface NewsApiService {

    suspend fun callNewsApi(): NewsResponse

}