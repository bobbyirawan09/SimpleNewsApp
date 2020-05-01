package bobby.irawan.simplenewsapp

import bobby.irawan.simplenewsapp.api.response.NewsResponse
import bobby.irawan.simplenewsapp.api.service.NewsApiService
import bobby.irawan.simplenewsapp.presentation.model.NewsModelView
import bobby.irawan.simplenewsapp.repository.NewsRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import kotlin.test.assertEquals

/**
 * Created by bobbyirawan09 on 01/05/20.
 */
class NewsRepositoryTest : Spek({

    val newsService = mockk<NewsApiService>()

    val newsRepo = NewsRepository(newsService)
    var newsModelView: NewsModelView? = null

    Feature("Call API headline news") {

        Scenario("Success calling API") {

            Given("init value and condition") {
                val dummyNewsResponse = NewsResponse(
                    status = "",
                    totalResults = 0,
                    code = "200",
                    error = "No error",
                    articles = mutableListOf()
                )
                coEvery { newsService.callNewsApi() } returns dummyNewsResponse
            }

            When("Request to API") {
                newsModelView = runBlocking { newsRepo.getHeadLineNews() }
            }

            Then("it should return news reponse, not error") {
                val isResultEmpty = newsModelView?.articles.isNullOrEmpty()
                assertEquals(true, isResultEmpty)
                coVerify { newsService.callNewsApi() }
            }
        }
    }
})