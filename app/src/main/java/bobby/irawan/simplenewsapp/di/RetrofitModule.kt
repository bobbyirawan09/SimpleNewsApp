package bobby.irawan.simplenewsapp.di

import bobby.irawan.simplenewsapp.data.api.service.NewsApi
import bobby.irawan.simplenewsapp.utils.Constants.BASE_API
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by bobbyirawan09 on 31/05/20.
 */

val retrofitModule = module {

    fun provideHttpClient() =
        OkHttpClient().newBuilder()
            .addInterceptor(HttpLoggingInterceptor())
            .connectTimeout(30, TimeUnit.SECONDS)
            .callTimeout(30, TimeUnit.SECONDS)
            .build()

    fun provideRetrofit(httpClient: OkHttpClient) =
        Retrofit.Builder().baseUrl(BASE_API)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()

    fun provideNewsApi(retrofit: Retrofit) = retrofit.create(NewsApi::class.java)

    single {
        provideHttpClient()
    }

    single {
        provideRetrofit(get())
    }

    single { provideNewsApi(get()) }
}