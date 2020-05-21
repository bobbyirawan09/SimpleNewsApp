package bobby.irawan.simplenewsapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bobby.irawan.simplenewsapp.presentation.model.NewsCategoryModelView
import bobby.irawan.simplenewsapp.repository.NewsRepositoryContract
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class CategoryViewModel(private val repository: NewsRepositoryContract) : ViewModel() {

    private var categories = listOf<NewsCategoryModelView>()
    private val viewModelJob = SupervisorJob()
    private val coroutineScope = viewModelScope + viewModelJob

    private val _newsCategoriesLiveData = MutableLiveData<List<NewsCategoryModelView>>()
    val newsCategoriesLiveData: LiveData<List<NewsCategoryModelView>>
        get() = _newsCategoriesLiveData

    fun getCategoryData() {
        coroutineScope.launch(Main) {
            categories = withContext(IO) { repository.getNewsCategory() }
            _newsCategoriesLiveData.value = categories
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
