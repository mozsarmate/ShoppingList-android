package hu.ait.shoppinglist.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.ait.shoppinglist.data.ItemDAO
import hu.ait.shoppinglist.data.ItemDatabase
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class ItemDataModule {
    @Provides
    fun provideTodoDao(appDatabase: ItemDatabase) : ItemDAO {
        return appDatabase.itemDao()
    }
    
    @Provides
    @Singleton
    fun provideTodoAppDatabase(
        @ApplicationContext appContext: Context
    ): ItemDatabase {
        return ItemDatabase.getDatabase(appContext)
    }
}