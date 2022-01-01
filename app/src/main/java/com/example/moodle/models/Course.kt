package com.example.moodle.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*



enum class Moed {
    A,
    B,
    C
}

enum class Semesters {
    A,
    B
}

enum class Days {
    SUNDAY,
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY
}


interface ITask {
    val id: Long
    val name: String
    val dueDate: Date
    val grade: Double
    val feedback: List<String>
    val isGraded: Boolean
    val isSubmitted: Boolean
}

data class HomeAssignment (
    override val id: Long,
    override val name: String,
    override val dueDate: Date,
    override val grade: Double,
    override val feedback: List<String>,
    override val isGraded: Boolean,
    override val isSubmitted: Boolean,
    val startDate: Date,
) : ITask

data class Test(
    override val id: Long,
    override val name: String,
    override val dueDate: Date,
    override val grade: Double,
    override val feedback: List<String>,
    override val isGraded: Boolean,
    override val isSubmitted: Boolean,
    val moed: Moed,
    val duration: Double
) : ITask

data class CourseSchedule (
    val courseScheduleId: Long,
    val courseScheduleDay: Days,
    val courseScheduleStartHour: Date,
    val courseScheduleDuration: Long
)



data class Forum (
    val forumId: Long
)

interface ICourse {
    val courseId: Long
    val courseName: String
    val courseLecturer: Long // Lecturer's id
    val courseSyllabus: String    // maybe later to change type
//    val courseForum: Forum
//    val courseSchedule: CourseSchedule
//    val courseHomeAssignments: List<HomeAssignment>
//    val courseTests: List<Test>
    //TODO: create join table for course/student participant
}

@Entity()
data class Course(
    @PrimaryKey(autoGenerate = true)
    override val courseId: Long,
    override val courseName: String,
    override val courseLecturer: Long,
    override val courseSyllabus: String,
//    override val courseForum: Forum,
//    override val courseSchedule: CourseSchedule,
//    override val courseHomeAssignments: List<HomeAssignment>,
//    override val courseTests: List<Test>
) : ICourse

data class CourseResponse (
    val course: List<Course> // why not livedata or flow
)
