package bobby.irawan.simplenewsapp.data.api.service

import bobby.irawan.simplenewsapp.data.api.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface NewsApi {

    @GET
    suspend fun getHeadlineNews(@Url url: String): NewsResponse

    @GET
    suspend fun getHeadlineNewsCategory(@Url url: String): NewsResponse

}