package bobby.irawan.simplenewsapp.presentation.viewmodel

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bobby.irawan.simplenewsapp.presentation.model.NewsArticleModelView
import bobby.irawan.simplenewsapp.utils.Constants.NEWS_ARTICLE_ARGS

class NewsDetailViewModel : ViewModel() {

    private var newsArticle = NewsArticleModelView()

    private val _newsArticleLiveData = MutableLiveData<NewsArticleModelView>()
    val newsArticleLiveData: LiveData<NewsArticleModelView>
        get() = _newsArticleLiveData

    fun retrieveArguments(arguments: Bundle?) {
        arguments?.let {
            newsArticle = it.getSerializable(NEWS_ARTICLE_ARGS) as NewsArticleModelView
            _newsArticleLiveData.postValue(newsArticle)
        }
    }

}
