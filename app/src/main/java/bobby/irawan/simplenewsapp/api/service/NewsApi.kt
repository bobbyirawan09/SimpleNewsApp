package bobby.irawan.simplenewsapp.api.service

import bobby.irawan.simplenewsapp.api.response.NewsResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Url

interface NewsApi {

    @GET
    suspend fun getHeadlineNews(@Url url: String): NewsResponse

}