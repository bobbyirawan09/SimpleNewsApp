package bobby.irawan.simplenewsapp.presentation.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import bobby.irawan.simplenewsapp.R
import bobby.irawan.simplenewsapp.databinding.FragmentNewsBinding
import bobby.irawan.simplenewsapp.presentation.base.BaseFragment
import bobby.irawan.simplenewsapp.presentation.model.NewsArticleModelView
import bobby.irawan.simplenewsapp.presentation.model.NewsModelView
import bobby.irawan.simplenewsapp.presentation.ui.adapter.NewsAdapter
import bobby.irawan.simplenewsapp.presentation.ui.newsdetail.NewsDetailActivity
import bobby.irawan.simplenewsapp.presentation.viewmodel.NewsViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.custom_toolbar.view.*
import kotlinx.android.synthetic.main.fragment_news.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : BaseFragment<FragmentNewsBinding>(),
    NewsAdapter.ClickListener {

    companion object {
        fun newInstance() =
            NewsFragment()
    }

    private val newsViewModel: NewsViewModel by viewModel()
    private val adapter =
        NewsAdapter()

    override fun main(view: View, savedInstanceState: Bundle?) {
        super.main(view, savedInstanceState)
        setToolbar()
        newsViewModel.getNewsData()
    }

    private fun setToolbar() {
        binding.toolbar.text_view_toolbar_title.text = resources.getString(R.string.app_name)
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
        newsViewModel.loadingStatus.observe(
            viewLifecycleOwner,
            Observer { status -> setProgressBar(status) }
        )
    }

    private fun setProgressBar(status: Boolean) {
        binding.showProgressBar = status
        binding.showList = !status
    }

    private fun showNewsList(newsModelView: NewsModelView?) {
        adapter.setClickListener(this)
        adapter.setNewsArticle(newsModelView?.articles)
        binding.recyclerViewNews.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewNews.adapter = adapter
        val divider =
            DividerItemDecoration(binding.recyclerViewNews.context, LinearLayoutManager.VERTICAL)
        binding.recyclerViewNews.addItemDecoration(divider)
    }

    fun showErrorSnackBar(message: String?) {
        val snackbar =
            Snackbar.make(constraint_layout_parent, message.orEmpty(), Snackbar.LENGTH_LONG)
        snackbar.view.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.red_error
            )
        )
        snackbar.show()
    }

    override fun onClickItemListener(newsArticle: NewsArticleModelView?) {
        NewsDetailActivity.startActivity(
            requireContext(),
            newsArticle
        )
    }

}
