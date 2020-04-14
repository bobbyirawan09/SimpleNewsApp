package bobby.irawan.simplenewsapp.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.api.load
import coil.transform.CircleCropTransformation

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

    }

}