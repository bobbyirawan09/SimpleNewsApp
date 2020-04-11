package bobby.irawan.simplenewsapp.di

import bobby.irawan.simplenewsapp.api.service.NewsApiServiceImpl
import org.koin.dsl.module

val serviceModule = module{

    single {
        NewsApiServiceImpl()
    }
}