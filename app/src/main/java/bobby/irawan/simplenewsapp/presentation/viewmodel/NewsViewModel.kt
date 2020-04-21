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

    private var news : NewsModelView? = null

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
            _loadingStatus.value = true
            viewModelScope.launch(Dispatchers.IO) {
                news = repositoryContract.getHeadLineNews()
                withContext(Dispatchers.Main) {
                    if (news != null) {
                        _newsLiveData.postValue(news)
                    } else {
                        _errorValue.value = "Data tidak dapat ditemukan"
                    }
                    _loadingStatus.value = false
                }
            }
    }

}
