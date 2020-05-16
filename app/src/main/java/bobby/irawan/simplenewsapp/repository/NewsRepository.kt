package bobby.irawan.simplenewsapp.repository

import bobby.irawan.simplenewsapp.data.api.response.NewsArticleResponse
import bobby.irawan.simplenewsapp.data.api.response.NewsResponse
import bobby.irawan.simplenewsapp.data.api.response.NewsSourceResponse
import bobby.irawan.simplenewsapp.data.api.service.NewsApiService
import bobby.irawan.simplenewsapp.data.local.NewsCategoryDAO
import bobby.irawan.simplenewsapp.data.local.NewsCategoryEntity
import bobby.irawan.simplenewsapp.presentation.model.NewsArticleModelView
import bobby.irawan.simplenewsapp.presentation.model.NewsCategoryModelView
import bobby.irawan.simplenewsapp.presentation.model.NewsModelView
import bobby.irawan.simplenewsapp.presentation.model.NewsSourceModelView
import bobby.irawan.simplenewsapp.utils.Constants.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepository constructor(
    private val api: NewsApiService,
    private val newsCategoryDAO: NewsCategoryDAO
) : NewsRepositoryContract {

    override suspend fun getHeadLineNews(): Response {
        val newsResponse = api.callNewsApi()
        return onCheckResponseNews(newsResponse)
    }

    override suspend fun getHeadLineNewsCategory(category: String): Response {
        val newsResponse = api.callNewsApiWithCategory(category)
        return onCheckResponseNews(newsResponse)
    }

    override suspend fun getNewsCategory(): List<NewsCategoryModelView> {
        val newsCategories = newsCategoryDAO.getNewsCategories()
        return newsCategories.value?.map {
            convertToCategoryModelView(it)
        } ?: listOf()
    }

    override suspend fun addNewsCategory(newsCategoryEntity: NewsCategoryEntity) {
        newsCategoryDAO.insert(newsCategoryEntity)
    }

    private suspend fun onCheckResponseNews(newsResponse: Response): Response {
        return when (newsResponse) {
            is Response.Success<*> -> Response.Success(convertResponseToModelView(newsResponse.data as NewsResponse))
            is Response.Error -> Response.Error(newsResponse.errorMessage)
        }
    }

    private suspend fun convertToCategoryModelView(entity: NewsCategoryEntity): NewsCategoryModelView {
        return withContext(Dispatchers.Default) {
            NewsCategoryModelView().apply {
                name = entity.name
                image = entity.image
                color = entity.color
            }
        }
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