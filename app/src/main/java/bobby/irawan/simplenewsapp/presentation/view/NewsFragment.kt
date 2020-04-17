package bobby.irawan.simplenewsapp.presentation.view

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import bobby.irawan.simplenewsapp.R
import bobby.irawan.simplenewsapp.presentation.adapter.NewsAdapter
import bobby.irawan.simplenewsapp.presentation.base.BaseFragment
import bobby.irawan.simplenewsapp.presentation.model.NewsModelView
import bobby.irawan.simplenewsapp.presentation.viewmodel.NewsViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_news.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : BaseFragment() {

    companion object {
        fun newInstance() = NewsFragment()
    }

    private val newsViewModel: NewsViewModel by viewModel()
    private val adapter = NewsAdapter()

    override fun main(view: View, savedInstanceState: Bundle?) {
        super.main(view, savedInstanceState)
        initRecyclerView()
        newsViewModel.getNewsData()
    }

    private fun initRecyclerView() {
        recycler_view_news.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recycler_view_news.setHasFixedSize(true)
        recycler_view_news.adapter = adapter
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
        adapter.setNewsArticle(newsModelView?.articles)
    }

    fun showErrorSnackBar(message: String?) {
        val snackbar = Snackbar.make(frame_layout_parent, message.orEmpty(), Snackbar.LENGTH_LONG)
        snackbar.view.setBackgroundColor(ContextCompat.getColor(context!!, R.color.red_error))
    }

}
