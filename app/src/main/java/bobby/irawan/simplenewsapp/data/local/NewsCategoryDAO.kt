package bobby.irawan.simplenewsapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by bobbyirawan09 on 02/05/20.
 */

@Dao
interface NewsCategoryDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg newsCategoryEntity: NewsCategoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(newsCategoriesEntity: List<NewsCategoryEntity>)

    @Query("SELECT * from news_category_table ORDER BY id ASC")
    suspend fun getNewsCategories(): List<NewsCategoryEntity>

    @Query("DELETE from news_category_table WHERE id = :id")
    suspend fun deleteCategory(id: Int)

}