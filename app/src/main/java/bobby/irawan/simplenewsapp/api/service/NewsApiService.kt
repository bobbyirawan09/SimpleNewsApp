package bobby.irawan.simplenewsapp.api.service

import bobby.irawan.simplenewsapp.api.response.NewsResponse
import bobby.irawan.simplenewsapp.presentation.NewsModelView
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Url

interface NewsApiService {

    suspend fun callNewsApi(): NewsModelView

}