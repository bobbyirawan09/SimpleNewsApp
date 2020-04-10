package bobby.irawan.simplenewsapp.api.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NewsArticleResponse(
    var newsSource: NewsSourceResponse? = null,
    var author: String? = "",
    var title: String? = "",
    var description: String? = "",
    var url: String? = "",
    @SerializedName("urlToImage")
    var urlImage: String? = "",
    var publishedAt: String? = "",
    var content: String? = ""
) : Serializable
