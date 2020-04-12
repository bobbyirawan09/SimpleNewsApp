package bobby.irawan.simplenewsapp.di

import bobby.irawan.simplenewsapp.api.service.NewsApiService
import bobby.irawan.simplenewsapp.repository.NewsRepository
import bobby.irawan.simplenewsapp.repository.NewsRepositoryContract
import org.koin.dsl.module

val repositoryModule = module {

    single<NewsRepositoryContract> {
        NewsRepository(get())
    }
}