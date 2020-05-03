package bobby.irawan.simplenewsapp.di

import bobby.irawan.simplenewsapp.data.api.service.NewsApiService
import bobby.irawan.simplenewsapp.data.api.service.NewsApiServiceImpl
import org.koin.dsl.module

val serviceModule = module{

    single<NewsApiService> {
        NewsApiServiceImpl()
    }
}