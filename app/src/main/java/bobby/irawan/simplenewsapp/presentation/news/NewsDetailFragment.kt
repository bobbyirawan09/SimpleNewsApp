package bobby.irawan.simplenewsapp.presentation.news

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import bobby.irawan.simplenewsapp.R
import bobby.irawan.simplenewsapp.databinding.FragmentNewsDetailBinding
import bobby.irawan.simplenewsapp.presentation.base.BaseFragment
import bobby.irawan.simplenewsapp.presentation.model.NewsArticleModelView
import bobby.irawan.simplenewsapp.presentation.viewmodel.NewsDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsDetailFragment : BaseFragment<FragmentNewsDetailBinding>() {

    companion object {
        fun newInstance() =
            NewsDetailFragment()
    }

    private val viewModel: NewsDetailViewModel by viewModel()

    override fun main(view: View, savedInstanceState: Bundle?) {
        super.main(view, savedInstanceState)
        viewModel.retrieveArguments(arguments)
    }

    override fun getLayoutId(): Int = R.layout.fragment_news_detail

    override fun observeViewModelChanges() {
        viewModel.newsArticleLiveData.observe(
            viewLifecycleOwner,
            Observer { newsArticle -> showNewsArticle(newsArticle) }
        )
    }

    private fun showNewsArticle(newsArticle: NewsArticleModelView?) {
        binding.newsArticle = newsArticle
    }

}
