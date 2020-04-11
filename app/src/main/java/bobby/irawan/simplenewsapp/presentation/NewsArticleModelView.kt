package bobby.irawan.simplenewsapp.presentation

import java.io.Serializable

open class NewsArticleModelView: Serializable {
    var newsSource: NewsSourceModelView? = null
    var author: String? = ""
    var title: String? = ""
    var description: String? = ""
    var url: String? = ""
    var urlImage: String? = ""
    var publishedAt: String? = ""
    var content: String? = ""
}