package bobby.irawan.simplenewsapp.api.service

import bobby.irawan.simplenewsapp.api.response.NewsArticleResponse
import bobby.irawan.simplenewsapp.api.response.NewsResponse
import bobby.irawan.simplenewsapp.api.response.NewsSourceResponse
import bobby.irawan.simplenewsapp.presentation.NewsArticleModelView
import bobby.irawan.simplenewsapp.presentation.NewsModelView
import bobby.irawan.simplenewsapp.presentation.NewsSourceModelView
import bobby.irawan.simplenewsapp.utils.ApiUtils
import bobby.irawan.simplenewsapp.utils.Constants.API_KEY
import bobby.irawan.simplenewsapp.utils.Constants.BASE_HEAD_LINE_NEWS_URL

class NewsApiServiceImpl : NewsApiService {

    override suspend fun callNewsApi(): NewsModelView {
        val url = BASE_HEAD_LINE_NEWS_URL + API_KEY
        val newsApi = ApiUtils.getRetrofitInstance().create(NewsApi::class.java)
        val response = newsApi.getHeadlineNews(url).await()
        return convertResponseToModelView(response)
    }

    private fun convertResponseToModelView(response: NewsResponse): NewsModelView {
        return NewsModelView().apply {
            totalResults = response.totalResults
            articles = generateArticlesModelView(response.articles)
        }
    }

    private fun generateArticlesModelView(articlesResponse: List<NewsArticleResponse>?): MutableList<NewsArticleModelView> {
        val articles = mutableListOf<NewsArticleModelView>()
        articlesResponse?.forEach {
            val article = NewsArticleModelView().apply {
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
        return NewsSourceModelView().apply {
            id = newsSource?.id
            name = newsSource?.name
        }
    }
}