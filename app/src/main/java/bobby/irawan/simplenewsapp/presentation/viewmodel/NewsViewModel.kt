package bobby.irawan.simplenewsapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bobby.irawan.simplenewsapp.presentation.model.NewsModelView
import bobby.irawan.simplenewsapp.repository.NewsRepositoryContract
import bobby.irawan.simplenewsapp.utils.Constants
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
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
            viewModelScope.launch(Main) {
                repositoryContract.getHeadLineNews()
                    .onStart { _loadingStatus.value = true }
                    .catch { exception ->
                        _errorValue.value = exception.message
                    }
                    .onCompletion {
                        _loadingStatus.value = false
                    }
                    .collect {
                        _newsLiveData.value = it
                    }
            }
        } else {
            _newsLiveData.postValue(news)
        }
    }

    fun getNewsDataWithCategory(category: String) {
        _loadingStatus.value = true
        viewModelScope.launch(Main) {
            repositoryContract.getHeadLineNewsCategory(category.toLowerCase(Locale.getDefault()))
                .onStart { _loadingStatus.value = true }
                .catch { exception ->
                    _errorValue.value = exception.message
                }
                .onCompletion {
                    _loadingStatus.value = false
                }
                .collect {
                    _newsLiveData.value = it
                }
        }
    }

}
