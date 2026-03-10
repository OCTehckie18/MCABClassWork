package com.example.mcabclasswork.Misc.database_INCLASS.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mcabclasswork.Misc.database_INCLASS.data.WishlistDao
import com.example.mcabclasswork.Misc.database_INCLASS.data.WishlistItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class WishlistViewModel(private val dao: WishlistDao) : ViewModel() {
    val items: Flow<List<WishlistItem>> = dao.getAllItems()


    fun addItem(name: String, description: String) {
        viewModelScope.launch {
            dao.insertItem(WishlistItem(title = name, description = description))
        }
    }


    fun deleteItem(item: WishlistItem) {
        viewModelScope.launch {
            dao.deleteItem(item)
        }
    }
}
