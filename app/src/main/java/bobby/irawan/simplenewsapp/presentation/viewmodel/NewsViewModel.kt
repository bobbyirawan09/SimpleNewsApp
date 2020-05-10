package bobby.irawan.simplenewsapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bobby.irawan.simplenewsapp.presentation.model.NewsModelView
import bobby.irawan.simplenewsapp.repository.NewsRepositoryContract
import bobby.irawan.simplenewsapp.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
            viewModelScope.launch(Dispatchers.IO) {
                val response = repositoryContract.getHeadLineNews()
                withContext(Dispatchers.Main) {
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
        } else {
            _newsLiveData.postValue(news)
        }
    }

    fun getNewsDataWithCategory(category: String) {
        _loadingStatus.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val response =
                repositoryContract.getHeadLineNewsCategory(category.toLowerCase(Locale.getDefault()))
            withContext(Dispatchers.Main) {
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

}
