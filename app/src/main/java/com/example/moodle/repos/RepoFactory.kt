package com.example.moodle.repos

import android.app.Application

object RepoFactory {
    lateinit var context: Application
    val courseRepo: CourseRepo = CourseRepoImpl
    val assignmentRepo: AssignmentRepo = AssignmentRepoImpl

}