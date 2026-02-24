package com.example.mcabclasswork.Misc.ViewModalDemo.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class Course(val name: String, val duration: String)

class CourseViewModel : ViewModel() {
    private val _courseList = MutableLiveData<List<Course>>(emptyList())
    val courseList: LiveData<List<Course>> = _courseList

    fun addCourse(name: String, duration: String) {
        val currentList = _courseList.value ?: emptyList()
        _courseList.value = currentList + Course(name, duration)
    }

    fun clearCourses() {
        _courseList.value = emptyList()
    }
}
