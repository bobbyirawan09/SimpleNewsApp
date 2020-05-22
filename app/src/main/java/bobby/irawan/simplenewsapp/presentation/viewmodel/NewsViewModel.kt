package bobby.irawan.simplenewsapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bobby.irawan.simplenewsapp.presentation.model.NewsModelView
import bobby.irawan.simplenewsapp.repository.NewsRepositoryContract
import bobby.irawan.simplenewsapp.utils.Constants
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import java.util.*

class NewsViewModel(private val repositoryContract: NewsRepositoryContract) : ViewModel() {

    private var news: NewsModelView? = null

    private val _newsLiveData = MutableLiveData<NewsModelView>()
    val newsLiveData: LiveData<NewsModelView>
        get() = _newsLiveData

    private val _loadingStatus = MutableLiveData<Boolean>()
    val loadingStatus: LiveData<Boolean>
        get() = _loadingStatus

    private val _errorValue = MutableLiveData<String>()
    val errorValue: LiveData<String>
        get() = _errorValue

    fun getNewsData() {
        if (news == null) {
            _loadingStatus.value = true
            viewModelScope.launch(Main) {
                val response = repositoryContract.getHeadLineNews()
                when (response) {
                    is Constants.Response.Success<*> -> {
                        news = response.data as NewsModelView
                        _newsLiveData.postValue(news)
                    }
                    is Constants.Response.Error -> _errorValue.value = response.errorMessage
                }
                _loadingStatus.value = false
            }
        } else {
            _newsLiveData.postValue(news)
        }
    }

    fun getNewsDataWithCategory(category: String) {
        _loadingStatus.value = true
        viewModelScope.launch(Main) {
            val response =
                repositoryContract.getHeadLineNewsCategory(category.toLowerCase(Locale.getDefault()))
            when (response) {
                is Constants.Response.Success<*> -> {
                    news = response.data as NewsModelView
                    _newsLiveData.postValue(news)
                }
                is Constants.Response.Error -> _errorValue.value = response.errorMessage
            }
            _loadingStatus.value = false
        }
    }

}
