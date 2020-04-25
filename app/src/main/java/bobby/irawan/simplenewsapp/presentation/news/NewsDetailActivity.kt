package bobby.irawan.simplenewsapp.presentation.news

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import bobby.irawan.simplenewsapp.R
import bobby.irawan.simplenewsapp.databinding.ActivityNewsDetailBinding
import bobby.irawan.simplenewsapp.presentation.model.NewsArticleModelView
import bobby.irawan.simplenewsapp.presentation.viewmodel.NewsDetailViewModel
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

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "News Detail"

        observeViewModelChanges()
        viewModel.retrieveIntent(intent)
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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
