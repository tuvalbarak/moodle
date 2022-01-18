package com.example.moodle.ui.dialogs

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.moodle.R
import com.example.moodle.models.Course
import com.example.moodle.models.HomeAssignment
import com.example.moodle.viewmodels.AssignmentViewModel
import com.example.moodle.viewmodels.CourseViewModel
import com.example.moodle.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.dialog_create_or_edit_assignment.*
import java.util.*

class CreateOrEditAssignmentDialog : DialogFragment(R.layout.dialog_create_or_edit_assignment) {

    private val assignmentViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory.create(requireContext())).get(AssignmentViewModel::class.java)
    }

    private val courseViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory.create(requireContext())).get(CourseViewModel::class.java)
    }

    private var currentCourse: Course? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        getSafeArgs()
    }

    private fun initUi() {

        dialog_add_or_create_assignment_btn_continue.setOnClickListener {
            //TODO - look for a proper replacement for deprecated Date constructor
            val assignment = HomeAssignment(
                id = System.currentTimeMillis(),
                name = dialog_add_or_create_assignment_tid_name.text.toString(),
                dueDate = Date(
                    dialog_add_or_create_assignment_due_date.year,
                    dialog_add_or_create_assignment_due_date.month + 1,
                    dialog_add_or_create_assignment_due_date.dayOfMonth
                ),
                grade = -1,
                feedback = "",
                isGraded = false,
                isSubmitted = false,
                givenDate = Date(
                    dialog_add_or_create_assignment_given_date.year,
                    dialog_add_or_create_assignment_given_date.month + 1,
                    dialog_add_or_create_assignment_given_date.dayOfMonth
                )
            )

            assignmentViewModel.insertAssignment(assignment)

            currentCourse?.let { course ->
//                course.assignments.add(assignment.id)
//                courseViewModel.updateCourse(course)
            }

            dismiss()
        }

        dialog_add_or_create_assignment_btn_cancel.setOnClickListener {
            dismiss()
        }
    }

    private fun getSafeArgs() {
        arguments?.let {
            CreateOrEditAssignmentDialogArgs.fromBundle(it).course?.also { course ->
                currentCourse = course
            }
        }

    }
}