package bobby.irawan.simplenewsapp.presentation.viewmodel

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bobby.irawan.simplenewsapp.presentation.model.NewsArticleModelView
import bobby.irawan.simplenewsapp.presentation.presentation.newsdetail.NewsDetailActivity.Companion.EXTRA_NEWS_ARTICLE

class NewsDetailViewModel : ViewModel() {

    private var newsArticle : NewsArticleModelView? = null

    private val _newsArticleLiveData = MutableLiveData<NewsArticleModelView?>()
    val newsArticleLiveData: LiveData<NewsArticleModelView?>
        get() = _newsArticleLiveData

    fun retrieveIntent(intent: Intent?) {
        if (intent != null) {
            newsArticle = intent.getSerializableExtra(EXTRA_NEWS_ARTICLE) as? NewsArticleModelView
            _newsArticleLiveData.postValue(newsArticle)
        }
    }

}
