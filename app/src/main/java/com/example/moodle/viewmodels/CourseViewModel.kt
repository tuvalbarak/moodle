package com.example.moodle.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moodle.models.Course
import com.example.moodle.models.HomeAssignment
import com.example.moodle.repos.CourseRepo
import com.example.moodle.utils.States
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
class CourseViewModel(private val courseRepo: CourseRepo, app: Application) : AndroidViewModel(app){
    //holds the state of the app
    val state = MutableLiveData<States>().apply {
        postValue(States.Idle)
    }

    //Current courses list
    val coursesList = MutableLiveData<List<Course>>().apply {
        viewModelScope.launch(Dispatchers.IO) {
            state.postValue(States.Loading)
            courseRepo.getAllCourses().collect { list ->
                postValue(list)
                state.postValue(States.Idle)
            }
        }
    }

    //Inserting a new course
    fun insertCourse(course: Course) {
        viewModelScope.launch(Dispatchers.IO) {
            state.postValue(States.Loading)
            courseRepo.insertCourse(course)
            state.postValue(States.Idle)
        }
    }

    //Updating an already existing course
    fun updateCourse(course: Course) {
        viewModelScope.launch(Dispatchers.IO) {
            state.postValue(States.Loading)
            courseRepo.updateCourse(course)
            state.postValue(States.Idle)
        }
    }

}

