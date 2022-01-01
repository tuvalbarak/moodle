package com.example.moodle

import android.app.Application
import com.example.moodle.repos.RepoFactory

object Initializer {
    fun init(application: Application) {
        RepoFactory.context = application
    }
}