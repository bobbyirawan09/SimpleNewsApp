package bobby.irawan.simplenewsapp.data.api.service

import bobby.irawan.simplenewsapp.data.api.response.NewsResponse
import bobby.irawan.simplenewsapp.utils.ApiUtils
import bobby.irawan.simplenewsapp.utils.Constants.API_KEY
import bobby.irawan.simplenewsapp.utils.Constants.BASE_NEWS_URL
import bobby.irawan.simplenewsapp.utils.Constants.CATEGORY_QUERY

class NewsApiServiceImpl : NewsApiService {

    override suspend fun callNewsApi(): NewsResponse? {
        val url = BASE_NEWS_URL + API_KEY
        val newsApi = ApiUtils.getRetrofitInstance().create(NewsApi::class.java)
        return try {
            newsApi.getHeadlineNews(url)
        } catch (e: Throwable) {
            return null
        }
    }

    override suspend fun callNewsApiWithCategory(category: String): NewsResponse? {
        val url = BASE_NEWS_URL + CATEGORY_QUERY + category + API_KEY
        val newsApi = ApiUtils.getRetrofitInstance().create(NewsApi::class.java)
        return try {
            newsApi.getHeadlineNews(url)
        } catch (e: Throwable) {
            return null
        }
    }
}