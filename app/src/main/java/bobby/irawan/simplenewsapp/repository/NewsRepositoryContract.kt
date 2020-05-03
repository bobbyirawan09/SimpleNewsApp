package bobby.irawan.simplenewsapp.repository

import bobby.irawan.simplenewsapp.data.local.NewsCategoryEntity
import bobby.irawan.simplenewsapp.presentation.model.NewsModelView

interface NewsRepositoryContract {

    suspend fun getHeadLineNews(): NewsModelView?

    suspend fun getHeadLineNewsCategory(category: String): NewsModelView?

    suspend fun getNewsCategory()

    suspend fun addNewsCategory(newsCategoryEntity: NewsCategoryEntity)

}