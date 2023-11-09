package hu.ait.shoppinglist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "itemtable")
data class Item(
    @PrimaryKey var id : Int,
    @ColumnInfo("name") var name: String,
    @ColumnInfo("category") var category: ItemCategory,
    @ColumnInfo("estPrice") var estPrice: Int,
    @ColumnInfo("description") var description: String,
    @ColumnInfo("isBought") var isBought: Boolean
)

enum class ItemCategory {
    FOOD, CLOTHES, ELECTRONICS, OTHER;
}