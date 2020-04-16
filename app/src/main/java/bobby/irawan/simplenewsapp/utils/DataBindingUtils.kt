package bobby.irawan.simplenewsapp.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.api.load

object DataBindingUtils {

    @BindingAdapter("bind:setImageCoil")
    @JvmStatic
    fun setImageCoil(imageView: ImageView, urlImage: String) {
        imageView.load(urlImage) {
            crossfade(true)
        }
    }

    @BindingAdapter("bind:setPublishDate")
    @JvmStatic
    fun setPublishDate(textView: TextView, publishDate: String) {
        textView.text = publishDate.parseServerDateFormatToString()
    }

    @BindingAdapter(value = ["bind:newsAuthor", "bind:newsSource"], requireAll = false)
    @JvmStatic
    fun setNewsSource(textView: TextView, author: String?, newsSource: String?) {
        if (!newsSource.isNullOrEmpty()) {
            textView.text = newsSource
        } else {
            textView.text = author.orEmpty()
        }
    }

}