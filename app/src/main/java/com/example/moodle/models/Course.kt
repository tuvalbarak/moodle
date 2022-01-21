package com.example.moodle.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


interface ICourse {
    val courseId: Long
    val courseName: String
    val courseLecturer: String
//    val courseSyllabus: String    // maybe later to change type
//    val courseForum: Forum
//    val courseSchedule: CourseSchedule
//    val courseHomeAssignments: List<HomeAssignment>
//    val courseTests: List<Test>
    //TODO: create join table for course/student participant
}

@Parcelize
@Entity
data class Course(
    @PrimaryKey(autoGenerate = true)
    override val courseId: Long,
    override val courseName: String,
    override val courseLecturer: String,
    var assignments: MutableList<Long>,
    val semester: Semester

) : ICourse, Parcelable

//data class Forum (
//    val forumId: Long
//)

//data class CourseSchedule (
//    val courseScheduleId: Long,
//    val courseScheduleDay: Days,
//    val courseScheduleStartHour: Date,
//    val courseScheduleDuration: Long
//)

//data class CourseResponse (
//    val course: List<Course> // why not livedata or flow
//)
