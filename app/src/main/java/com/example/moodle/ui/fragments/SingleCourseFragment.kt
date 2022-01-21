package com.example.moodle.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.moodle.R
import com.example.moodle.models.Course
import com.example.moodle.models.HomeAssignment
import com.example.moodle.ui.adapters.VerticalAssignmentAdapter
import com.example.moodle.utils.ProgressBar
import com.example.moodle.utils.States
import com.example.moodle.viewmodels.AssignmentViewModel
import com.example.moodle.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_single_course.*


class SingleCourseFragment : Fragment(R.layout.fragment_single_course) {

    private val assignmentViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory.create(requireContext())).get(AssignmentViewModel::class.java)
    }

    private var currentCourse: Course? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        initObservers()
        getSafeArgs()
    }

    private fun initUi() {
        handleAssignmentsRecycleView()
        initListeners()
    }

    private fun initListeners() {
        fragment_single_course_fab_new_assignment.setOnClickListener {
            view?.findNavController()?.navigate(
                SingleCourseFragmentDirections.navGraphActionCreateOrEditAssignment(currentCourse)
            )
        }
    }

    private fun initObservers() {

        assignmentViewModel.currentCourseAssignmentsList.observe(viewLifecycleOwner, { assignments ->
            (fragment_single_course_rv_assignments.adapter as VerticalAssignmentAdapter).submitList(assignments)
            Log.d(TAG, assignments.toString())
        })

        assignmentViewModel.state.observe(viewLifecycleOwner, { state ->
            when(state) {
                States.Loading -> ProgressBar.instance().show(context)
                States.Idle -> ProgressBar.instance().dismiss()
                else -> ProgressBar.instance().dismiss()
            }
        })
    }

    private fun getSafeArgs() {
        arguments?.let {
            SingleCourseFragmentArgs.fromBundle(it).course?.also { course ->
                currentCourse = course
                assignmentViewModel.getAssignmentsById(course.assignments)
                fragment_single_course_tv_name.text = course.courseName
            }
        }
    }

    private fun handleAssignmentsRecycleView() {

        val onAssignmentClickListener: (assignment: HomeAssignment) -> Unit = { assignment ->
            Log.d(TAG, "${assignment.name} was clicked")
//            view?.findNavController()?.navigate(
//                HomeFragmentDirections.navGraphActionSingleAssignmentFragment(assignment)
//            )
        }

        fragment_single_course_rv_assignments.adapter = VerticalAssignmentAdapter(onAssignmentClickListener)
    }

    companion object {
        const val TAG = "SingleCourseFragmentTAG"
    }

}