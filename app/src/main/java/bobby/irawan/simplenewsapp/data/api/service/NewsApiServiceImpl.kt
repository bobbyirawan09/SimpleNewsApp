package bobby.irawan.simplenewsapp.data.api.service

import bobby.irawan.simplenewsapp.data.api.response.NewsResponse
import bobby.irawan.simplenewsapp.utils.Constants.API_KEY
import bobby.irawan.simplenewsapp.utils.Constants.BASE_NEWS_URL
import bobby.irawan.simplenewsapp.utils.Constants.CATEGORY_QUERY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewsApiServiceImpl(private val api: NewsApi) : NewsApiService {

    override suspend fun callNewsApi(): Flow<NewsResponse> {
        val url = BASE_NEWS_URL + API_KEY
        return flow {
            emit(api.getHeadlineNews(url))
        }
    }

    override suspend fun callNewsApiWithCategory(category: String): Flow<NewsResponse> {
        val url = BASE_NEWS_URL + CATEGORY_QUERY + category + API_KEY
        return flow {
            emit(api.getHeadlineNews(url))
        }
    }
}