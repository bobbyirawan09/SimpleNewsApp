package bobby.irawan.simplenewsapp.api.service

import bobby.irawan.simplenewsapp.api.response.NewsResponse
import bobby.irawan.simplenewsapp.utils.ApiUtils
import bobby.irawan.simplenewsapp.utils.Constants.API_KEY
import bobby.irawan.simplenewsapp.utils.Constants.BASE_HEAD_LINE_NEWS_URL

class NewsApiServiceImpl : NewsApiService {

    override suspend fun callNewsApi(): NewsResponse {
        val url = BASE_HEAD_LINE_NEWS_URL + API_KEY
        val newsApi = ApiUtils.getRetrofitInstance().create(NewsApi::class.java)
        return newsApi.getHeadlineNews(url)
    }
}