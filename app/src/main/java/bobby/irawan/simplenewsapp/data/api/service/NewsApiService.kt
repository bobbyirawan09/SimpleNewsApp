package bobby.irawan.simplenewsapp.data.api.service

import bobby.irawan.simplenewsapp.utils.Constants.Response

interface NewsApiService {

    suspend fun callNewsApi(): Response
    suspend fun callNewsApiWithCategory(category: String): Response

}