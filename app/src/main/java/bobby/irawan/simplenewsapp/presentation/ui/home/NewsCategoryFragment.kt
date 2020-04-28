package bobby.irawan.simplenewsapp.presentation.ui.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import bobby.irawan.simplenewsapp.R
import bobby.irawan.simplenewsapp.databinding.FragmentCategoryBinding
import bobby.irawan.simplenewsapp.presentation.base.BaseFragment
import bobby.irawan.simplenewsapp.presentation.model.NewsCategoryModelView
import bobby.irawan.simplenewsapp.presentation.ui.adapter.NewsCategoryAdapter
import bobby.irawan.simplenewsapp.presentation.viewmodel.NewsCategoryViewModel
import kotlinx.android.synthetic.main.custom_toolbar.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsCategoryFragment : BaseFragment<FragmentCategoryBinding>() {

    companion object {
        fun newInstance() = NewsCategoryFragment()
    }

    private val viewModelNews: NewsCategoryViewModel by viewModel()
    private val adapter = NewsCategoryAdapter()

    override fun main(view: View, savedInstanceState: Bundle?) {
        super.main(view, savedInstanceState)

        setToolbar()
        setRecyclerView()
        viewModelNews.getCategoryData()
    }

    private fun setRecyclerView() {
        binding.recyclerViewNewsCategory.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerViewNewsCategory.setHasFixedSize(true)
        binding.recyclerViewNewsCategory.adapter = adapter
    }

    private fun setToolbar() {
        binding.toolbar.text_view_toolbar_title.text =
            resources.getString(R.string.news_category_title)
    }

    override fun getLayoutId(): Int = R.layout.fragment_category

    override fun observeViewModelChanges() {
        viewModelNews.newsCategoriesLiveData.observe(
            viewLifecycleOwner,
            Observer { categories -> setDataToAdapter(categories) }
        )
    }

    private fun setDataToAdapter(categories: List<NewsCategoryModelView>) {
        adapter.setCategories(categories = categories)
    }

}
