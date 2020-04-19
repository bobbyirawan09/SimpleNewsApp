package bobby.irawan.simplenewsapp.presentation.news

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import bobby.irawan.simplenewsapp.R
import bobby.irawan.simplenewsapp.databinding.FragmentNewsBinding
import bobby.irawan.simplenewsapp.presentation.adapter.NewsAdapter
import bobby.irawan.simplenewsapp.presentation.base.BaseFragment
import bobby.irawan.simplenewsapp.presentation.model.NewsArticleModelView
import bobby.irawan.simplenewsapp.presentation.model.NewsModelView
import bobby.irawan.simplenewsapp.presentation.viewmodel.NewsViewModel
import bobby.irawan.simplenewsapp.utils.Constants.NEWS_ARTICLE_ARGS
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_news.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : BaseFragment<FragmentNewsBinding>(), NewsAdapter.ClickListener {

    companion object {
        fun newInstance() =
            NewsFragment()
    }

    private val newsViewModel: NewsViewModel by viewModel()
    private val adapter = NewsAdapter()

    override fun main(view: View, savedInstanceState: Bundle?) {
        super.main(view, savedInstanceState)
        initRecyclerView()
        newsViewModel.getNewsData()
    }

    private fun initRecyclerView() {
        adapter.setClickListener(this)
        binding.recyclerViewNews.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewNews.adapter = adapter
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
        snackbar.view.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.red_error
            )
        )
    }

    override fun onClickItemListener(newsArticle: NewsArticleModelView?) {
        val bundle = bundleOf(NEWS_ARTICLE_ARGS to newsArticle)
        findNavController().navigate(R.id.action_news_to_news_detail, bundle)
    }

}
