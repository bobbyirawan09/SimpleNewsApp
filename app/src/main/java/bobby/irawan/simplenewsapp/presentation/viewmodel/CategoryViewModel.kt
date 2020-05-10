package bobby.irawan.simplenewsapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bobby.irawan.simplenewsapp.presentation.model.NewsCategoryModelView
import bobby.irawan.simplenewsapp.repository.NewsRepository
import bobby.irawan.simplenewsapp.repository.NewsRepositoryContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoryViewModel(private val repository: NewsRepositoryContract) : ViewModel() {

    private var categories = listOf<NewsCategoryModelView>()

    private val _newsCategoriesLiveData = MutableLiveData<List<NewsCategoryModelView>>()
    val newsCategoriesLiveData: LiveData<List<NewsCategoryModelView>>
        get() = _newsCategoriesLiveData

    fun getCategoryData() {
        viewModelScope.launch(Dispatchers.IO) {
            categories = repository.getNewsCategory()
            withContext(Dispatchers.Main) {
                _newsCategoriesLiveData.value = categories
            }
        }
    }
}
