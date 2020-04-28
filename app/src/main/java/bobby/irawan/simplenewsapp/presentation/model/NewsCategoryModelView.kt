package bobby.irawan.simplenewsapp.presentation.model

import bobby.irawan.simplenewsapp.R
import java.io.Serializable

/**
 * Created by bobbyirawan09 on 28/04/20.
 */
data class NewsCategoryModelView(
    var name: String? = "",
    var image: Int? = 0
) : Serializable {

    companion object {

        fun getCategories(): List<NewsCategoryModelView> {
            return listOf(
                NewsCategoryModelView("Entertainment", R.drawable.entertainment_illustration),
                NewsCategoryModelView("Business", R.drawable.business_illustration),
                NewsCategoryModelView("Health", R.drawable.health_illustration),
                NewsCategoryModelView("Science", R.drawable.science_illustration),
                NewsCategoryModelView("Sports", R.drawable.sports_illustration),
                NewsCategoryModelView("Technology", R.drawable.technology_illustration)
            )
        }
    }
}