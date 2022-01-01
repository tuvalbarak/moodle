package com.example.moodle

import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.moodle.dao.CourseDao
import com.example.moodle.models.Course
import com.example.moodle.repos.RepoFactory

private const val DB_NAME = "moodle"

/**
 * DB reference is implemented as singleton, gaining source of truth throughout the app
 */

@Database(entities = [
    Course::class
], version = 1)
internal abstract class AppDatabase : RoomDatabase() {
    abstract val courseDao: CourseDao

    companion object {
        var dbInstance: AppDatabase? = null

        fun instance() = dbInstance ?: run {
            dbInstance = databaseBuilder(RepoFactory.context, AppDatabase::class.java, DB_NAME).build()
            dbInstance!!
        }
    }
}