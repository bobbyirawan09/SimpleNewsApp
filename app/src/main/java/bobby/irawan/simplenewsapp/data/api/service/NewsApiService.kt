package bobby.irawan.simplenewsapp.data.api.service

import bobby.irawan.simplenewsapp.data.api.response.NewsResponse
import bobby.irawan.simplenewsapp.utils.Constants.Response
import kotlinx.coroutines.flow.Flow

interface NewsApiService {

    suspend fun callNewsApi(): Flow<NewsResponse>
    suspend fun callNewsApiWithCategory(category: String): Flow<NewsResponse>

}