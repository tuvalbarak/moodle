package com.example.moodle.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moodle.models.Course
import kotlinx.coroutines.flow.Flow

@Dao
internal interface CourseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCourse(course: Course)

//    @Query("SELECT * FROM favorites ORDER By timeStampAdded DESC")
//    fun getFavoriteCourses(): Flow<List<Course>>

    @Query("SELECT * FROM Course")
    fun getAllCourses(): Flow<List<Course>>


    //TODO: query for different courses sorting
}