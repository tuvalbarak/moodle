package com.example.moodle.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.core.os.HandlerCompat.postDelayed
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.moodle.R
import com.example.moodle.models.Course
import com.example.moodle.models.HomeAssignment
import com.example.moodle.ui.adapters.HorizontalAssignmentAdapter
import com.example.moodle.ui.adapters.CourseAdapter
import com.example.moodle.ui.dialogs.CreateOrEditCourseDialog
import com.example.moodle.utils.ProgressBar
import com.example.moodle.utils.States
import com.example.moodle.viewmodels.AssignmentViewModel
import com.example.moodle.viewmodels.CourseViewModel
import com.example.moodle.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val courseViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory.create(requireContext())).get(CourseViewModel::class.java)
    }


    private val assignmentViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory.create(requireContext())).get(AssignmentViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        initObservers()
    }

    private fun initUi() {
        handleAssignmentsRecycleView()
        handleCoursesRecyclerView()
        initListeners()
    }

    private fun handleAssignmentsRecycleView() {

        val onAssignmentClickListener: (assignment: HomeAssignment) -> Unit = { assignment ->
            Log.d(TAG, "${assignment.name} was clicked")
            view?.findNavController()?.navigate(
                HomeFragmentDirections.navGraphActionSingleAssignmentFragment(assignment)
            )
        }

        fragment_home_rv_assignments.adapter = HorizontalAssignmentAdapter(onAssignmentClickListener)
    }

    private fun handleCoursesRecyclerView() {
        val onCourseClickListener: (course: Course) -> Unit = { course ->
            Log.d(TAG, "${course.courseName} was clicked")
            //Navigating to SingleCourseFragment, sending it the clicked course
            view?.findNavController()?.navigate(
                HomeFragmentDirections.navGraphActionSingleCourseFragment(course)
            )
        }

        fragment_home_rv_courses.adapter = CourseAdapter(onCourseClickListener)
    }

    private fun initObservers() {
        courseObservers()
        assignmentObservers()
    }

    private fun courseObservers() {
        courseViewModel.coursesList.observe(viewLifecycleOwner, { courses ->
            (fragment_home_rv_courses.adapter as CourseAdapter).submitList(courses)
        })

        courseViewModel.state.observe(viewLifecycleOwner, { state ->
            when(state) {
                States.Loading -> ProgressBar.instance().show(context)
                States.Idle -> ProgressBar.instance().dismiss()
                else -> ProgressBar.instance().dismiss()
            }
        })
    }

    private fun assignmentObservers() {
        assignmentViewModel.allAssignmentsList.observe(viewLifecycleOwner, { assignments ->
            (fragment_home_rv_assignments.adapter as HorizontalAssignmentAdapter).submitList(assignments)
        })

        assignmentViewModel.state.observe(viewLifecycleOwner, { state ->
            when(state) {
                States.Loading -> {}
                States.Idle -> {}
                else -> ProgressBar.instance().dismiss()
            }
        })
    }

    private fun initListeners() {
        fragment_home_fab_new_course_button.setOnClickListener {
            CreateOrEditCourseDialog().show(childFragmentManager, TAG)
        }
    }

    companion object {
        const val TAG = "HomeFragmentTag"
    }

}