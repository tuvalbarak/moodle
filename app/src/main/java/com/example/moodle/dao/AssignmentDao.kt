package com.example.moodle.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moodle.models.HomeAssignment
import kotlinx.coroutines.flow.Flow

@Dao
internal interface AssignmentDao {

    /** Inserting new assignment */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAssignment(assignment: HomeAssignment)

    /** Get all assignments */
    @Query("SELECT * FROM HomeAssignment ORDER BY name ASC")
    fun getAllAssignments(): Flow<List<HomeAssignment>>

    /** Get a specific assignment by its id */
    @Query("SELECT * FROM HomeAssignment WHERE id LIKE :assignmentId ORDER BY name ASC")
    fun getAssignmentById(assignmentId: Long): HomeAssignment


}