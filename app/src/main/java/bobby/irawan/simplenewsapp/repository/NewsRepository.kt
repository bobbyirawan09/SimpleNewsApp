package bobby.irawan.simplenewsapp.repository

import bobby.irawan.simplenewsapp.api.response.NewsArticleResponse
import bobby.irawan.simplenewsapp.api.response.NewsResponse
import bobby.irawan.simplenewsapp.api.response.NewsSourceResponse
import bobby.irawan.simplenewsapp.api.service.NewsApiService
import bobby.irawan.simplenewsapp.presentation.model.NewsArticleModelView
import bobby.irawan.simplenewsapp.presentation.model.NewsModelView
import bobby.irawan.simplenewsapp.presentation.model.NewsSourceModelView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepository constructor(private val api: NewsApiService) : NewsRepositoryContract {

    override suspend fun getHeadLineNews(): NewsModelView? {
        val newsResponse = api.callNewsApi()
        val newsModelView = convertResponseToModelView(newsResponse)
        return newsModelView
    }

    override suspend fun getHeadLineNewsCategory(category: String): NewsModelView? {
        val newsResponse = api.callNewsApiWithCategory(category)
        val newsModelView = convertResponseToModelView(newsResponse)
        return newsModelView
    }

    private suspend fun convertResponseToModelView(response: NewsResponse?): NewsModelView? =
        withContext(Dispatchers.Default) {
            response?.let {
                NewsModelView().apply {
                    totalResults = it.totalResults
                    articles = generateArticlesModelView(it.articles)
                }
            }
        }

    private fun generateArticlesModelView(articlesResponse: List<NewsArticleResponse>?): MutableList<NewsArticleModelView> {
        val articles = mutableListOf<NewsArticleModelView>()
        articlesResponse?.forEach {
            val article = NewsArticleModelView()
                .apply {
                    author = it.author
                    content = it.content
                    description = it.description
                    newsSource = generateNewsSource(it.newsSource)
                    publishedAt = it.publishedAt
                    title = it.title
                    url = it.url
                    urlImage = it.urlImage
                }
            articles.add(article)
        }
        return articles
    }

    private fun generateNewsSource(newsSource: NewsSourceResponse?): NewsSourceModelView? {
        return NewsSourceModelView()
            .apply {
                id = newsSource?.id
                name = newsSource?.name
            }
    }

}