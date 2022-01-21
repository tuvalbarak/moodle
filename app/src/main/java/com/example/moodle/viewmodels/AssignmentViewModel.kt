package com.example.moodle.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moodle.models.HomeAssignment
import com.example.moodle.repos.AssignmentRepo
import com.example.moodle.utils.States
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

class AssignmentViewModel(private val assignmentRepo: AssignmentRepo, app: Application) : AndroidViewModel(app) {

    val state = MutableLiveData<States>().apply {
        postValue(States.Idle)
    }

    val allAssignmentsList = MutableLiveData<List<HomeAssignment>>().apply {
        viewModelScope.launch(Dispatchers.IO) {
            state.postValue(States.Loading)
            assignmentRepo.getAllAssignments().collect { list ->
                postValue(list)
                state.postValue(States.Idle)
            }
        }
    }

    val currentCourseAssignmentsList = MutableLiveData<List<HomeAssignment>>()

    fun insertAssignment(assignment: HomeAssignment) {
        viewModelScope.launch(Dispatchers.IO) {
            state.postValue(States.Loading)
            assignmentRepo.insertAssignment(assignment)
            state.postValue(States.Idle)
        }
    }

    fun getAssignmentsById(assignmentIds: List<Long>) {
        viewModelScope.launch(Dispatchers.IO) {
            state.postValue(States.Loading)
            val tempList = mutableListOf<HomeAssignment>()
            assignmentIds.forEach {
                tempList.add(assignmentRepo.getAssignmentById(it))
            }
            currentCourseAssignmentsList.postValue(tempList)
            state.postValue(States.Idle)
        }
    }

}