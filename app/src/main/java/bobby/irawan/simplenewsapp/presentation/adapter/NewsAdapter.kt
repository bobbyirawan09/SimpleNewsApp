package bobby.irawan.simplenewsapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import bobby.irawan.simplenewsapp.R
import bobby.irawan.simplenewsapp.databinding.RowNewsBinding
import bobby.irawan.simplenewsapp.presentation.model.NewsArticleModelView

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var newsArticles = listOf<NewsArticleModelView>()

    fun setNewsArticle(newsArticles: List<NewsArticleModelView>) {
        this.newsArticles = newsArticles
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val bindingAdapter = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.row_news,
            parent,
            false
        ) as RowNewsBinding
        return NewsViewHolder(bindingAdapter)
    }

    override fun getItemCount(): Int {
        return newsArticles.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsArticles[position])
    }

    class NewsViewHolder(binding: RowNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(newsArticle: NewsArticleModelView) {

        }
    }

}