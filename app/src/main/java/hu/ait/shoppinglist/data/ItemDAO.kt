package hu.ait.shoppinglist.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDAO {

        @Query("SELECT * from itemtable")
        fun getAllItems(): Flow<List<Item>>
        
        @Query("SELECT * from itemtable WHERE id = :id")
        fun getItem(id: Int): Flow<Item>
        
        @Query("SELECT COUNT(*) from itemtable")
        suspend fun getCount(): Int
        
        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insert(todo: Item)
        
        @Update
        suspend fun update(todo: Item)
        
        @Delete
        suspend fun delete(todo: Item)
        
        @Query("DELETE from itemtable")
        suspend fun deleteAll()
    
}