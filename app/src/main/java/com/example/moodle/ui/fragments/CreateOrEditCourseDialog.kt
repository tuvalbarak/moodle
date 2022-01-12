package com.example.moodle.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.moodle.R
import com.example.moodle.models.Course
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

    }

    private fun initUi() {
        //take care of edit mode (example in contacts)

        fragment_add_or_create_course_btn_continue.setOnClickListener {
            val course = Course(
                courseId = System.currentTimeMillis(),
                courseName = fragment_add_or_create_course_tid_name.text.toString(),
                courseLecturer = fragment_add_or_create_course_tid_lecturer.text.toString(),
                assignments = emptyList()
            )

            courseViewModel.insertCourse(course)
            dismiss()
        }

        fragment_add_contact_btn_cancel.setOnClickListener {
            dismiss()
        }
    }

}