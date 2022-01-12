package com.example.moodle.dao

import androidx.room.*
import com.example.moodle.models.Course
import kotlinx.coroutines.flow.Flow

@Dao
internal interface CourseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCourse(course: Course)

    @Query("SELECT * FROM Course ORDER BY courseName ASC")
    fun getAllCourses(): Flow<List<Course>>

    @Update
    fun updateCourse(course: Course)

}