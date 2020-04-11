package bobby.irawan.simplenewsapp.presentation

import java.io.Serializable

open class NewsModelView: Serializable {
    var totalResults: Int? = 0
    var articles: MutableList<NewsArticleModelView>? = mutableListOf()
}