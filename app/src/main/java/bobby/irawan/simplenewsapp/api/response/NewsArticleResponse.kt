package bobby.irawan.simplenewsapp.api.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NewsArticleResponse(
    val newsSource: NewsSourceResponse? = null,
    val author: String? = "",
    val title: String? = "",
    val description: String? = "",
    val url: String? = "",
    @SerializedName("urlToImage")
    val urlImage: String? = "",
    val publishedAt: String? = "",
    val content: String? = ""
) : Serializable
