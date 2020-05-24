package bobby.irawan.simplenewsapp.repository

import bobby.irawan.simplenewsapp.data.local.NewsCategoryEntity
import bobby.irawan.simplenewsapp.presentation.model.NewsCategoryModelView
import bobby.irawan.simplenewsapp.presentation.model.NewsModelView
import kotlinx.coroutines.flow.Flow

interface NewsRepositoryContract {

    suspend fun getHeadLineNews(): Flow<NewsModelView?>

    suspend fun getHeadLineNewsCategory(category: String): Flow<NewsModelView?>

    suspend fun getNewsCategory(): Flow<List<NewsCategoryModelView>>

    suspend fun addNewsCategory(newsCategoryEntity: NewsCategoryEntity)

}