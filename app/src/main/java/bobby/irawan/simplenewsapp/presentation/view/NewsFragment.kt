package bobby.irawan.simplenewsapp.presentation.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import bobby.irawan.simplenewsapp.R
import bobby.irawan.simplenewsapp.presentation.adapter.NewsAdapter
import bobby.irawan.simplenewsapp.presentation.base.BaseFragment
import bobby.irawan.simplenewsapp.presentation.model.NewsModelView
import bobby.irawan.simplenewsapp.presentation.viewmodel.NewsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : BaseFragment() {

    companion object {
        fun newInstance() = NewsFragment()
    }

    private val newsViewModel: NewsViewModel by viewModel()
    private val adapter by lazy { NewsAdapter() }

    override fun main(view: View, savedInstanceState: Bundle?) {
        super.main(view, savedInstanceState)

    }

    override fun getLayoutId(): Int = R.layout.fragment_news

    override fun observeViewModelChanges() {
        newsViewModel.errorValue.observe(
            viewLifecycleOwner,
            Observer { message -> showErrorSnackBar(message) })
        newsViewModel.newsLiveData.observe(
            viewLifecycleOwner,
            Observer { newsModelView -> showNewsList(newsModelView) }
        )
    }

    private fun showNewsList(newsModelView: NewsModelView?) {
        //Will show list in adapter
    }

    fun showErrorSnackBar(message: String?) {
        //Will showing snackbar
        //Will showing error state
    }

}
