package bobby.irawan.simplenewsapp.repository

import bobby.irawan.simplenewsapp.data.api.response.NewsArticleResponse
import bobby.irawan.simplenewsapp.data.api.response.NewsResponse
import bobby.irawan.simplenewsapp.data.api.response.NewsSourceResponse
import bobby.irawan.simplenewsapp.data.api.service.NewsApiService
import bobby.irawan.simplenewsapp.data.firebase.FirebaseService
import bobby.irawan.simplenewsapp.data.local.NewsCategoryDAO
import bobby.irawan.simplenewsapp.data.local.NewsCategoryEntity
import bobby.irawan.simplenewsapp.presentation.model.*
import bobby.irawan.simplenewsapp.utils.Constants
import bobby.irawan.simplenewsapp.utils.Constants.Response
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class NewsRepository constructor(
    private val firebase: FirebaseService,
    private val api: NewsApiService,
    private val newsCategoryDAO: NewsCategoryDAO
) : NewsRepositoryContract {

    private val dispatchersDefault = Dispatchers.Default

    override suspend fun getHeadLineNews(): Flow<NewsModelView?> {
        val newsResponse = api.callNewsApi()
        return newsResponse.map { response ->
            convertResponseToModelView(response)
        }.flowOn(dispatchersDefault)
    }

    override suspend fun getHeadLineNewsCategory(category: String): Flow<NewsModelView?> {
        val newsResponse = api.callNewsApiWithCategory(category)
        return newsResponse.map { response ->
            convertResponseToModelView(response)
        }.flowOn(dispatchersDefault)
    }

    override suspend fun getNewsCategory(): Flow<List<NewsCategoryModelView>> {
        return flow {
            emit(newsCategoryDAO.getNewsCategories().map {
                convertToCategoryModelView(it)
            })
        }.flowOn(dispatchersDefault)
    }

    override suspend fun addNewsCategory(newsCategoryEntity: NewsCategoryEntity) {
        newsCategoryDAO.insert(newsCategoryEntity)
    }

    override suspend fun onLoginWithEmail(email: String, password: String): Response {
        val convertedResponse: Response
        val response = firebase.onLoginWithEmail(email, password)
        when (response) {
            is Response.Success<*> -> {
                val authResult = response.data as AuthResult?
                convertedResponse =
                    Response.Success(convertToUserModelView(user = authResult?.user))
            }
            is Response.Error -> {
                convertedResponse = Response.Error(response.errorMessage)
            }
        }
        return convertedResponse
    }

    override suspend fun onSignUpWithEmail(email: String, password: String): Response {
        val convertedResponse: Response
        val response = firebase.onSignUpWithEmail(email, password)
        when (response) {
            is Response.Success<*> -> {
                val authResult = response.data as AuthResult?
                convertedResponse =
                    Response.Success(convertToUserModelView(user = authResult?.user))
            }
            is Response.Error -> {
                convertedResponse = Response.Error(response.errorMessage)
            }
        }
        return convertedResponse
    }

    override suspend fun onLoginWithSocialMedia(userToken: String?, socialMedia: Constants.SocialMedia) : Response {
        val convertedResponse: Response
        val response = firebase.onLoginWithSocialMedia(userToken ?: "", socialMedia)
        when (response) {
            is Response.Success<*> -> {
                val authResult = response.data as AuthResult?
                convertedResponse =
                    Response.Success(convertToUserModelView(user = authResult?.user))
            }
            is Response.Error -> {
                convertedResponse = Response.Error(response.errorMessage)
            }
        }
        return convertedResponse
    }

    private fun convertToUserModelView(user: FirebaseUser?) = UserModelView().apply {
        uid = user?.uid
        displayName = user?.displayName
        email = user?.email
        imageUrl = user?.photoUrl
    }

    private fun convertToCategoryModelView(entity: NewsCategoryEntity): NewsCategoryModelView =
        NewsCategoryModelView().apply {
            name = entity.name
            image = entity.image
            color = entity.color
        }

    private fun convertResponseToModelView(response: NewsResponse?): NewsModelView? =
        response?.let {
            NewsModelView().apply {
                totalResults = it.totalResults
                articles = generateArticlesModelView(it.articles)
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