package bobby.irawan.simplenewsapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bobby.irawan.simplenewsapp.presentation.model.NewsModelView
import bobby.irawan.simplenewsapp.repository.NewsRepositoryContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsViewModel(private val repositoryContract: NewsRepositoryContract) : ViewModel() {

    private var news = NewsModelView()

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
        try {
            _loadingStatus.value = true
            viewModelScope.launch(Dispatchers.IO) {
                news = repositoryContract.getHeadLineNews()
                withContext(Dispatchers.Main) {
                    _newsLiveData.postValue(news)
                }
            }
        } catch (e: Throwable) {
            _errorValue.value = e.message
        } finally {
            _loadingStatus.value = false
        }
    }

}
