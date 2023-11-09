package hu.ait.shoppinglist.screen

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.ait.shoppinglist.data.Item
import hu.ait.shoppinglist.data.ItemCategory
import hu.ait.shoppinglist.data.ItemDAO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val itemDAO: ItemDAO) : ViewModel() {
    
    fun getAllItems(): Flow<List<Item>> {
        return itemDAO.getAllItems()
    }
    
    suspend fun getCount(): Int {
        return itemDAO.getCount()
    }
    
    fun insert(item: Item) {
        viewModelScope.launch {
            Log.d("MAIN", "insert: ")
            itemDAO.insert(item)
        }
    }
    
    fun update(item: Item) {
        viewModelScope.launch {
            itemDAO.update(item)
        }
    }
    
    fun delete(item: Item) {
        viewModelScope.launch {
            itemDAO.delete(item)
        }
    }
    
    fun deleteAll() {
        viewModelScope.launch {
            itemDAO.deleteAll()
        }
    }
    
    fun getIcon(item : Item) : ImageVector {
        if (item.category == ItemCategory.FOOD) return Icons.Filled.ShoppingCart
        if (item.category == ItemCategory.CLOTHES) return Icons.Filled.Person
        if (item.category == ItemCategory.ELECTRONICS) return Icons.Filled.ThumbUp
        return Icons.Filled.MoreVert
    }
}
