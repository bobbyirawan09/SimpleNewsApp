package bobby.irawan.simplenewsapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bobby.irawan.simplenewsapp.presentation.model.NewsCategoryModelView

class CategoryViewModel : ViewModel() {

    private var categories = listOf<NewsCategoryModelView>()

    private val _newsCategoriesLiveData = MutableLiveData<List<NewsCategoryModelView>>()
    val newsCategoriesLiveData: LiveData<List<NewsCategoryModelView>>
        get() = _newsCategoriesLiveData

    fun getCategoryData() {
        categories = NewsCategoryModelView.getCategories()
        _newsCategoriesLiveData.value = categories
    }
}
