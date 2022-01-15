package com.example.moodle.repos

import com.example.moodle.AppDatabase
import com.example.moodle.models.HomeAssignment
import kotlinx.coroutines.flow.Flow


interface AssignmentRepo {
    suspend fun getAllAssignments(): Flow<List<HomeAssignment>>
    suspend fun getAssignmentById(assignmentId: Long): HomeAssignment
    suspend fun insertAssignment(assignment: HomeAssignment)
}

internal object AssignmentRepoImpl : AssignmentRepo {
    override suspend fun getAllAssignments(): Flow<List<HomeAssignment>> =
        AppDatabase.instance().assignmentDao.getAllAssignments()

    override suspend fun getAssignmentById(assignmentId: Long): HomeAssignment =
        AppDatabase.instance().assignmentDao.getAssignmentById(assignmentId)

    override suspend fun insertAssignment(assignment: HomeAssignment) {
        AppDatabase.instance().assignmentDao.insertAssignment(assignment)
    }

}