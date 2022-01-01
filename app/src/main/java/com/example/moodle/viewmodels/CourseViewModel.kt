package com.example.moodle.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.moodle.repos.CourseRepo
import com.example.moodle.utils.States

class CourseViewModel(private val courseRepo: CourseRepo, app: Application) : AndroidViewModel(app){
    //holds the state of the app
    val state = MutableLiveData<States>().apply {
        postValue(States.Idle)
    }



    // TODO: Add logic immpl
}
