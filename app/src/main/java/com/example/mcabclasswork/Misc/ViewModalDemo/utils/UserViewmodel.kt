package com.example.mcabclasswork.Misc.ViewModalDemo.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class User(val name: String, val age: Int)

class UserViewModel : ViewModel() {
    private val _userList = MutableLiveData<List<User>>(emptyList())
    val userList: LiveData<List<User>> = _userList

    fun addUser(name: String, age: Int) {
        val currentList = _userList.value ?: emptyList()
        _userList.value = currentList + User(name, age)
    }

    fun clearUsers() {
        _userList.value = emptyList()
    }
}
