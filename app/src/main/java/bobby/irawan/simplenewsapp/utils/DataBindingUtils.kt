package bobby.irawan.simplenewsapp.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import bobby.irawan.simplenewsapp.R
import bobby.irawan.simplenewsapp.utils.Constants.DATE_FORMAT_DAY
import bobby.irawan.simplenewsapp.utils.Constants.EMPTY
import coil.api.load
import coil.size.Scale

object DataBindingUtils {

    @BindingAdapter("bind:setImageCoil")
    @JvmStatic
    fun setImageCoil(imageView: ImageView, urlImage: String?) {
        imageView.load(urlImage) {
            crossfade(true)
            scale(Scale.FILL)
            error(R.drawable.ic_no_image)
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

    @BindingAdapter(value = ["bind:newsDetailAuthor", "bind:newsDetailSource"], requireAll = false)
    @JvmStatic
    fun setNewsDetailAuthor(textView: TextView, author: String?, newsSource: String?) {
        var newsDetailAuthor = EMPTY
        when {
            !author.isNullOrEmpty() && !newsSource.isNullOrEmpty() -> newsDetailAuthor =
                "$author \u2022 $newsSource"
            !author.isNullOrEmpty() && newsSource.isNullOrEmpty() -> newsDetailAuthor = "$author"
            author.isNullOrEmpty() && !newsSource.isNullOrEmpty() -> newsDetailAuthor =
                "$newsSource"
        }
        textView.text = newsDetailAuthor
    }

    @BindingAdapter(value = ["bind:showDateInDay"], requireAll = false)
    @JvmStatic
    fun setDateInDay(textView: TextView, date: String?) {
        textView.text = date?.parseServerDateFormatToString(newFormat = DATE_FORMAT_DAY)
    }

}