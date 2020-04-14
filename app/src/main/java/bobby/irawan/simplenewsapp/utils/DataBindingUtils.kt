package bobby.irawan.simplenewsapp.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter

object DataBindingUtils {

    @BindingAdapter("bind:setImageCoil")
    @JvmStatic
    fun setImageCoil(imageView: ImageView, urlImage: String) {

    }

    @BindingAdapter("bind:setPublishDate")
    @JvmStatic
    fun setPublishDate(textView: TextView, publishDate: String) {

    }

}