package com.example.moodle.repos

import com.example.moodle.AppDatabase
import com.example.moodle.models.Course
import kotlinx.coroutines.flow.Flow

interface CourseRepo {
    suspend fun getAllCourses(): Flow<List<Course>>
}

internal object CourseRepoImpl : CourseRepo {
    override suspend fun getAllCourses(): Flow<List<Course>> =
        AppDatabase.instance().courseDao.getAllCourses()

}