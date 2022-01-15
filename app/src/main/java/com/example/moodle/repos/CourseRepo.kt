package com.example.moodle.repos

import com.example.moodle.AppDatabase
import com.example.moodle.models.Course
import kotlinx.coroutines.flow.Flow

interface CourseRepo {
    suspend fun getAllCourses(): Flow<List<Course>>
    suspend fun insertCourse(course: Course)
    suspend fun updateCourse(course: Course)
}

internal object CourseRepoImpl : CourseRepo {
    override suspend fun getAllCourses(): Flow<List<Course>> =
        AppDatabase.instance().courseDao.getAllCourses()

    override suspend fun insertCourse(course: Course) {
        AppDatabase.instance().courseDao.insertCourse(course)
    }

    override suspend fun updateCourse(course: Course) {
        AppDatabase.instance().courseDao.updateCourse(course)
    }

}