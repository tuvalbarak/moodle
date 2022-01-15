package com.example.moodle.models

import androidx.room.TypeConverter
import com.google.gson.Gson

object Converters {

    /** Converters for lists of Longs*/
    @TypeConverter
    fun listToJson(value: List<Long>): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String): List<Long> = Gson().fromJson(value, Array<Long>::class.java).toList()

    /** Converters for Semester model*/
    @TypeConverter
    fun semesterToString(value: Semester): String = value.name

    @TypeConverter
    fun stringToSemester(value: String): Semester = when(value) {
        Semester.A.name -> Semester.A
        Semester.B.name -> Semester.B
        Semester.C.name -> Semester.C
        else -> Semester.UNKNOWN
    }

}