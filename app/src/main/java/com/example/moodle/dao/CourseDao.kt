package com.example.moodle.dao

import androidx.room.*
import com.example.moodle.models.Course
import kotlinx.coroutines.flow.Flow

@Dao
internal interface CourseDao {

    /** Inserting new course */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCourse(course: Course)

    /** Get all courses */
    @Query("SELECT * FROM Course ORDER BY courseName ASC")
    fun getAllCourses(): Flow<List<Course>>

    /** Updating a course */
    @Update
    fun updateCourse(course: Course)

}