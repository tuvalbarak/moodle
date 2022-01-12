package com.example.moodle.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moodle.repos.RepoFactory

/**
* Using Factory design pattern for a scalable solution to VM approach
 */
object ViewModelFactory {
    fun create(context: Context) : ViewModelProvider.AndroidViewModelFactory =
        ViewModelFactoryImpl(context.applicationContext as Application)
}

@Suppress("UNCHECKED_CAST")
private class ViewModelFactoryImpl(val app: Application) : ViewModelProvider.AndroidViewModelFactory(app) {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when(modelClass) {
        CourseViewModel::class.java -> CourseViewModel(RepoFactory.courseRepo, app) as T
        AssignmentViewModel::class.java -> AssignmentViewModel(RepoFactory.assignmentRepo, app) as T
        //TODO: add other necessery viewmodel
        else -> throw NotImplementedError(modelClass.toString())
    }
}