package bobby.irawan.simplenewsapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

/**
 * Created by bobbyirawan09 on 02/05/20.
 */

@Database(entities = [NewsCategoryEntity::class], version = 1, exportSchema = false)
abstract class NewsCategoryDatabase() : RoomDatabase() {

    abstract fun newsCategoryDao(): NewsCategoryDAO

    companion object {

        @Volatile
        private var INSTANCE: NewsCategoryDatabase? = null

        fun getDatabase(context: Context): NewsCategoryDatabase? {
            if (INSTANCE == null) {
                synchronized(NewsCategoryDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            NewsCategoryDatabase::class.java, "news_category_database"
                        )
                            .addCallback(prepopulateCallBack(context))
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE
        }

        fun prepopulateCallBack(context: Context) = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                Executors.newSingleThreadExecutor().execute(object : Runnable {
                    override fun run() {
                        CoroutineScope(Dispatchers.IO).launch {
                            getDatabase(context)?.newsCategoryDao()
                                ?.insertAll(NewsCategoryEntity.getCategories())
                        }
                    }
                })
            }
        }
    }
}