package com.example.moodle.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.Date

interface ITask {
    val id: Long
    val name: String?
    val dueDate: Date
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
    override val dueDate: Date,
    override val grade: Int?,
    override val feedback: String?,
    override val isGraded: Boolean?,
    override val isSubmitted: Boolean?,
) : ITask, Parcelable

//data class Test(
//    override val id: Long,
//    override val name: String,
////    override val dueDate: LocalDate,
//    override val grade: Int,
//    override val feedback: String,
//    override val isGraded: Boolean,
//    override val isSubmitted: Boolean,
//    val moed: Moed,
//    val duration: Double
//) : ITask