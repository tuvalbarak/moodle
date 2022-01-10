package com.example.moodle.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.moodle.R
import com.example.moodle.models.Course
import com.example.moodle.viewmodels.CourseViewModel
import com.example.moodle.viewmodels.ViewModelFactory

class HomeFragment : Fragment(R.layout.fragment_home) {
    //Using the ViewModelFactory I created to create a VM to this fragment.
    private val courseViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory.create(requireContext())).get(CourseViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val course1: Course = Course(1, "Math", 21234, "Sylabus")
        val course2: Course = Course(2, "Infi", 213556, "Sylabus")
        val course3: Course = Course(3, "Scien", 215780, "Sylabus")

        courseViewModel.insertCourse(course1)
        courseViewModel.insertCourse(course2)
        courseViewModel.insertCourse(course3)

        initObservers()
    }

    private fun initObservers() {
        courseViewModel.coursesList.observe(viewLifecycleOwner, { courses ->
            Log.d(TAG, courses.toString())
        })
    }

    companion object {
        const val TAG = "HomeFragmentTag"
    }

}