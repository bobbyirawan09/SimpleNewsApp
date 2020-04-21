package bobby.irawan.simplenewsapp.api.response

import java.io.Serializable

data class NewsResponse(
    val status: String? = "",
    val totalResults: Int? = 0,
    var articles: List<NewsArticleResponse>? = listOf(),
    var code: String? = "",
    var error: String? = ""
) : Serializable