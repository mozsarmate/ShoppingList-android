package hu.ait.shoppinglist.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.ait.shoppinglist.data.Item
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
}
