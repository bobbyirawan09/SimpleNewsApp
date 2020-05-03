package bobby.irawan.simplenewsapp.di

import bobby.irawan.simplenewsapp.data.local.NewsCategoryDatabase
import org.koin.dsl.module

/**
 * Created by bobbyirawan09 on 02/05/20.
 */

val databaseModule = module {
    single {
        NewsCategoryDatabase.getDatabase(get())
    }

    single { get<NewsCategoryDatabase>().newsCategoryDao() }

}