package bobby.irawan.simplenewsapp.presentation.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import bobby.irawan.simplenewsapp.R
import bobby.irawan.simplenewsapp.databinding.RowNewsCategoryBinding
import bobby.irawan.simplenewsapp.presentation.model.NewsCategoryModelView
import java.util.*

/**
 * Created by bobbyirawan09 on 28/04/20.
 */

class NewsCategoryAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listener: ClickListener? = null

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NewsCategoryModelView>() {

        override fun areItemsTheSame(
            oldItem: NewsCategoryModelView,
            newItem: NewsCategoryModelView
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: NewsCategoryModelView,
            newItem: NewsCategoryModelView
        ): Boolean {
            return oldItem.name == newItem.name
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    fun setCategories(categories: List<NewsCategoryModelView>) {
        differ.submitList(categories)
    }

    fun setClickListener(listener: ClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val bindingAdapter = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.row_news_category,
            parent,
            false
        ) as RowNewsCategoryBinding
        return NewsCategoryAdapter.NewsCategoryViewHolder(
            bindingAdapter,
            listener
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when {
            holder is NewsCategoryViewHolder -> holder.bind(differ.currentList.get(position))
        }
    }

    class NewsCategoryViewHolder(
        val binding: RowNewsCategoryBinding,
        val listener: ClickListener?
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(category: NewsCategoryModelView) {
            binding.category = category
            binding.cardViewCategoryBusiness.setOnClickListener {
                listener?.onClickListener(category)
            }
            binding.textViewCategoryName.text = category.name?.toUpperCase(Locale.getDefault())
        }
    }

    interface ClickListener {
        fun onClickListener(category: NewsCategoryModelView)
    }

}