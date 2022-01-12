package com.example.moodle.models

import android.icu.util.LocaleData
import android.os.Parcelable
import android.util.Log
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject
import java.time.LocalDate
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
    val name: String?
//    val dueDate: LocalDate
    val grade: Int?
    val feedback: String?
    val isGraded: Boolean?
    val isSubmitted: Boolean?
}

@Parcelize
@Entity
data class HomeAssignment (
    @PrimaryKey(autoGenerate = true)
    override val id: Long,
    override val name: String?,
//    override val dueDate: LocalDate,
    override val grade: Int?,
    override val feedback: String?,
    override val isGraded: Boolean?,
    override val isSubmitted: Boolean?
//    val startDate: LocalDate,
) : ITask, Parcelable

class Converters {

    @TypeConverter
    fun listToJson(value: List<Long>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Long>::class.java).toList()
}

//class DateConverter {
//    init {
//        Log.d("yoyo", "here")
//    }
//
//    @TypeConverter
//    fun converter(date: LocaleData?): String =
//        //do something
//         JSONObject().apply {
//             put("key1", "somevalue")
//             put("key2", "other value")
//         }.toString()
//
//
//
//
//
//
//}

data class Test(
    override val id: Long,
    override val name: String,
//    override val dueDate: LocalDate,
    override val grade: Int,
    override val feedback: String,
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
//    override val courseSyllabus: String,
//    override val courseForum: Forum,
//    override val courseSchedule: CourseSchedule,
//    override val courseHomeAssignments: List<HomeAssignment>,
//    override val courseTests: List<Test>
    var assignments: List<Long>

) : ICourse, Parcelable

data class CourseResponse (
    val course: List<Course> // why not livedata or flow
)
