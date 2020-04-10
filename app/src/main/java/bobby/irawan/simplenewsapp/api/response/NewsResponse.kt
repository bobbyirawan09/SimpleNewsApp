package bobby.irawan.simplenewsapp.api.response

import java.io.Serializable

data class NewsResponse(
    var status: String? = "",
    val totalResults: Int? = 0,
    var articles: List<NewsArticleResponse>? = null
) : Serializable