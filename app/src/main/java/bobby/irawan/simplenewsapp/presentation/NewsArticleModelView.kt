package bobby.irawan.simplenewsapp.presentation

import bobby.irawan.simplenewsapp.api.response.NewsSourceResponse
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class NewsArticleModelView: Serializable {
    var newsSource: NewsSourceModelView? = null
    var author: String? = ""
    var title: String? = ""
    var description: String? = ""
    var url: String? = ""
    var urlImage: String? = ""
    var publishedAt: String? = ""
    var content: String? = ""
}