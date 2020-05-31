package bobby.irawan.simplenewsapp.presentation.presentation.newsdetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import bobby.irawan.simplenewsapp.R
import bobby.irawan.simplenewsapp.databinding.ActivityNewsDetailBinding
import bobby.irawan.simplenewsapp.presentation.model.NewsArticleModelView
import bobby.irawan.simplenewsapp.presentation.viewmodel.NewsDetailViewModel
import kotlinx.android.synthetic.main.custom_toolbar.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NEWS_ARTICLE = "newsArticleArgs"

        fun startActivity(context: Context, newsArticle: NewsArticleModelView?) {
            val intent = Intent(context, NewsDetailActivity::class.java)
            intent.putExtra(EXTRA_NEWS_ARTICLE, newsArticle)
            context.startActivity(intent)
        }
    }

    private val binding by lazy {
        DataBindingUtil.setContentView(
            this,
            R.layout.activity_news_detail
        ) as ActivityNewsDetailBinding
    }
    private val viewModel: NewsDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setToolbar()

        observeViewModelChanges()
        viewModel.retrieveIntent(intent)
    }

    private fun setToolbar() {
        binding.toolbar.image_view_back_button.visibility = View.VISIBLE
        binding.toolbar.image_view_back_button.setOnClickListener { finish() }
        //do something here with the layout
    }

    private fun observeViewModelChanges() {
        viewModel.newsArticleLiveData.observe(
            this,
            Observer { newsArticle -> showNewsArticle(newsArticle) }
        )
    }

    private fun showNewsArticle(newsArticle: NewsArticleModelView?) {
        binding.newsArticle = newsArticle
    }
}
