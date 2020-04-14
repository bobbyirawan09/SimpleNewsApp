package bobby.irawan.simplenewsapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import bobby.irawan.simplenewsapp.R
import bobby.irawan.simplenewsapp.databinding.RowNewsBinding
import bobby.irawan.simplenewsapp.presentation.model.NewsArticleModelView

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var newsArticles: MutableList<NewsArticleModelView>? = mutableListOf()

    fun setNewsArticle(newsArticles: MutableList<NewsArticleModelView>?) {
        this.newsArticles = newsArticles
        notifyDataSetChanged()
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
        return newsArticles?.size ?: 0
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.binding.newsArticle = newsArticles?.getOrNull(position)
    }

    open class NewsViewHolder(val binding: RowNewsBinding) : RecyclerView.ViewHolder(binding.root)

}