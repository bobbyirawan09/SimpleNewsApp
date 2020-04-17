package bobby.irawan.simplenewsapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import bobby.irawan.simplenewsapp.R
import bobby.irawan.simplenewsapp.databinding.RowNewsBinding
import bobby.irawan.simplenewsapp.databinding.RowNewsHeadlineBinding
import bobby.irawan.simplenewsapp.presentation.model.NewsArticleModelView
import bobby.irawan.simplenewsapp.utils.Constants.TYPE_HEADLINE
import bobby.irawan.simplenewsapp.utils.Constants.TYPE_LIST

class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NewsArticleModelView>() {

        override fun areItemsTheSame(
            oldItem: NewsArticleModelView,
            newItem: NewsArticleModelView
        ): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: NewsArticleModelView,
            newItem: NewsArticleModelView
        ): Boolean {
            return oldItem.url == newItem.url &&
                    oldItem.title == newItem.title &&
                    oldItem.content == newItem.content
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    fun setNewsArticle(newsArticles: MutableList<NewsArticleModelView>?) {
        differ.submitList(newsArticles)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_HEADLINE) {
            val bindingAdapter = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.row_news_headline,
                parent,
                false
            ) as RowNewsHeadlineBinding
            return NewsHeadlineViewHolder(bindingAdapter)
        } else {
            val bindingAdapter = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.row_news,
                parent,
                false
            ) as RowNewsBinding
            return NewsViewHolder(bindingAdapter)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TYPE_HEADLINE) {
            val headlineHolder = holder as NewsHeadlineViewHolder
            headlineHolder.binding.newsArticle = differ.currentList.get(position)
        } else {
            val listHolder = holder as NewsViewHolder
            listHolder.binding.newsArticle = differ.currentList.get(position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return TYPE_HEADLINE
        } else {
            return TYPE_LIST
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class NewsViewHolder(val binding: RowNewsBinding) : RecyclerView.ViewHolder(binding.root)

    class NewsHeadlineViewHolder(val binding: RowNewsHeadlineBinding) :
        RecyclerView.ViewHolder(binding.root)

}