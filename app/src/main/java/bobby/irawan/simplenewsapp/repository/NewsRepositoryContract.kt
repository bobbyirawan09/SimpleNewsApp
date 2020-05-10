package bobby.irawan.simplenewsapp.repository

import bobby.irawan.simplenewsapp.data.local.NewsCategoryEntity
import bobby.irawan.simplenewsapp.presentation.model.NewsCategoryModelView
import bobby.irawan.simplenewsapp.utils.Constants.Response

interface NewsRepositoryContract {

    suspend fun getHeadLineNews(): Response

    suspend fun getHeadLineNewsCategory(category: String): Response

    suspend fun getNewsCategory(): List<NewsCategoryModelView>

    suspend fun addNewsCategory(newsCategoryEntity: NewsCategoryEntity)

}