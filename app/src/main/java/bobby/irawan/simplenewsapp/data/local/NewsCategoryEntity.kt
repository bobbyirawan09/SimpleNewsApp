package bobby.irawan.simplenewsapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import bobby.irawan.simplenewsapp.R
import java.io.Serializable

/**
 * Created by bobbyirawan09 on 02/05/20.
 */

@Entity(tableName = "news_category_table")
data class NewsCategoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int? = null,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "image")
    var image: Int,

    @ColumnInfo(name = "color")
    var color: Int
) : Serializable {

    companion object {

        fun getCategories(): List<NewsCategoryEntity> {
            return listOf(
                NewsCategoryEntity(
                    name = "Entertainment",
                    image = R.drawable.entertainment_illustration,
                    color = 0
                ),
                NewsCategoryEntity(
                    name = "Business",
                    image = R.drawable.business_illustration,
                    color = 0
                ),
                NewsCategoryEntity(
                    name = "Health",
                    image = R.drawable.health_illustration,
                    color = 0
                ),
                NewsCategoryEntity(
                    name = "Science",
                    image = R.drawable.science_illustration,
                    color = 0
                ),
                NewsCategoryEntity(
                    name = "Sports",
                    image = R.drawable.sports_illustration,
                    color = 0
                ),
                NewsCategoryEntity(
                    name = "Technology",
                    image = R.drawable.technology_illustration,
                    color = 0
                )
            )
        }
    }
}