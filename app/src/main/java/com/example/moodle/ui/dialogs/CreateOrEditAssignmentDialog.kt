package com.example.moodle.ui.dialogs

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.moodle.R
import com.example.moodle.extensions.toDateDayMonthAndYear
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

            val givenDate = Calendar.getInstance().apply {
                set(Calendar.YEAR, dialog_add_or_create_assignment_given_date.year)
                set(Calendar.DAY_OF_MONTH, dialog_add_or_create_assignment_given_date.dayOfMonth)
                set(Calendar.MONTH, dialog_add_or_create_assignment_given_date.month)
                set(Calendar.HOUR, 0)
                set(Calendar.MINUTE, 0)
            }.time

            val dueDate = Calendar.getInstance().apply {
                set(Calendar.YEAR, dialog_add_or_create_assignment_due_date.year)
                set(Calendar.DAY_OF_MONTH, dialog_add_or_create_assignment_due_date.dayOfMonth)
                set(Calendar.MONTH, dialog_add_or_create_assignment_due_date.month)
                set(Calendar.HOUR, 0)
                set(Calendar.MINUTE, 0)
            }.time

            val assignment = HomeAssignment(
                id = System.currentTimeMillis(),
                name = dialog_add_or_create_assignment_tid_name.text.toString(),
                dueDate = dueDate,
                grade = -1,
                feedback = "",
                isGraded = false,
                isSubmitted = false,
                givenDate = givenDate
            )

            assignmentViewModel.insertAssignment(assignment)

            currentCourse?.let { course ->
                val updatedAssignments = mutableListOf<Long>()
                updatedAssignments.addAll(course.assignments)
                updatedAssignments.add(assignment.id)
                course.assignments = updatedAssignments

                courseViewModel.updateCourse(course)
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