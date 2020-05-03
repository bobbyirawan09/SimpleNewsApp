package bobby.irawan.simplenewsapp.presentation.model

import java.io.Serializable

/**
 * Created by bobbyirawan09 on 28/04/20.
 */
data class NewsCategoryModelView(
    var name: String? = "",
    var image: Int? = 0,
    var color: Int? = 0
) : Serializable