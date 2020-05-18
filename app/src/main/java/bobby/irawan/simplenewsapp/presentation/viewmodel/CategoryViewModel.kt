package bobby.irawan.simplenewsapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bobby.irawan.simplenewsapp.presentation.model.NewsCategoryModelView
import bobby.irawan.simplenewsapp.repository.NewsRepositoryContract
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoryViewModel(private val repository: NewsRepositoryContract) : ViewModel() {

    private var categories = listOf<NewsCategoryModelView>()
    private val viewModelJob = SupervisorJob()

    private val _newsCategoriesLiveData = MutableLiveData<List<NewsCategoryModelView>>()
    val newsCategoriesLiveData: LiveData<List<NewsCategoryModelView>>
        get() = _newsCategoriesLiveData

    fun getCategoryData() {
        viewModelScope.launch(Main + viewModelJob) {
            categories = withContext(IO + viewModelJob) { repository.getNewsCategory() }
            _newsCategoriesLiveData.value = categories
        }
    }
}
