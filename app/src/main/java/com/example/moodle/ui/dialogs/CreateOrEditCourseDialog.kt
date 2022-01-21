package com.example.moodle.ui.dialogs

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.moodle.R
import com.example.moodle.models.Course
import com.example.moodle.models.Semester
import com.example.moodle.utils.ProgressBar
import com.example.moodle.utils.States
import com.example.moodle.viewmodels.CourseViewModel
import com.example.moodle.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.dialog_create_or_edit_course.*

class CreateOrEditCourseDialog: DialogFragment(R.layout.dialog_create_or_edit_course) {

    private val courseViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory.create(requireContext())).get(CourseViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        initObservers()
    }

    private fun initUi() {
        //take care of edit mode (example in contacts) - currently supporting only create mode

        dialog_add_or_create_course_btn_continue.setOnClickListener {

            val semester = when(dialog_add_or_create_course_tid_semester.text.toString().uppercase()) {
                Semester.A.name -> Semester.A
                Semester.B.name -> Semester.B
                Semester.C.name -> Semester.C
                else -> Semester.UNKNOWN
            }

            val course = Course(
                courseId = System.currentTimeMillis(),
                courseName = dialog_add_or_create_course_tid_name.text.toString(),
                courseLecturer = dialog_add_or_create_course_tid_lecturer.text.toString(),
                assignments = mutableListOf(),
                semester = semester
            )

            courseViewModel.insertCourse(course)
            dismiss()
        }

        dialog_add_contact_btn_cancel.setOnClickListener {
            dismiss()
        }
    }

    private fun initObservers() {
        courseViewModel.state.observe(viewLifecycleOwner, { state ->
            when(state) {
                States.Loading -> ProgressBar.instance().show(context)
                States.Idle -> ProgressBar.instance().dismiss()
                else -> ProgressBar.instance().dismiss()
            }
        })
    }
}