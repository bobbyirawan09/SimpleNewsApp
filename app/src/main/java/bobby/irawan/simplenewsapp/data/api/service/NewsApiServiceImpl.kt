package bobby.irawan.simplenewsapp.data.api.service

import bobby.irawan.simplenewsapp.utils.ApiUtils
import bobby.irawan.simplenewsapp.utils.Constants.API_KEY
import bobby.irawan.simplenewsapp.utils.Constants.BASE_NEWS_URL
import bobby.irawan.simplenewsapp.utils.Constants.CATEGORY_QUERY
import bobby.irawan.simplenewsapp.utils.Constants.Response

class NewsApiServiceImpl : NewsApiService {

    override suspend fun callNewsApi(): Response {
        val url = BASE_NEWS_URL + API_KEY
        val newsApi = ApiUtils.getRetrofitInstance().create(NewsApi::class.java)
        return try {
            val response = newsApi.getHeadlineNews(url)
            if (response.isSuccessful) {
                Response.Success(response.body())
            } else {
                Response.Error(response.errorBody().toString())
            }
        } catch (e: Throwable) {
            Response.Error(e.message.toString())
        }
    }

    override suspend fun callNewsApiWithCategory(category: String): Response {
        val url = BASE_NEWS_URL + CATEGORY_QUERY + category + API_KEY
        val newsApi = ApiUtils.getRetrofitInstance().create(NewsApi::class.java)
        return try {
            val response = newsApi.getHeadlineNews(url)
            if (response.isSuccessful) {
                Response.Success(response.body())
            } else {
                Response.Error(response.errorBody().toString())
            }
        } catch (e: Throwable) {
            Response.Error(e.message.toString())
        }
    }
}