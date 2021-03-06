package com.example.moodle.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moodle.R
import com.example.moodle.models.Course
import kotlinx.android.synthetic.main.item_course.view.*


class CourseAdapter(
    private val onCourseClickListener: (course: Course) -> Unit
) : ListAdapter<Course, CourseViewHolder>(CourseDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder =
        CourseViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_course, parent, false),
            onCourseClickListener
        )

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class CourseViewHolder(
    itemView: View,
    private val onCourseClickListener: (course: Course) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    fun bind(course: Course) {
        itemView.apply {
            item_course_tv_title.text = course.courseName
            item_course_tv_lecturer.text = course.courseLecturer
            item_course_tv_semester.text = course.semester.name

            this.setOnClickListener {
                onCourseClickListener.invoke(course)
            }
        }
    }
}

/**
 * Using Diffutils to compare between two Courses.
 */
object CourseDiffCallback : DiffUtil.ItemCallback<Course>() {
    override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean = oldItem.courseId == newItem.courseId
    override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean = oldItem == newItem
}






//package com.example.moodle.ui.adapters
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import com.example.moodle.R
//import com.example.moodle.models.Course
//import kotlinx.android.extensions.LayoutContainer
//import kotlinx.android.synthetic.main.item_course.*
//import kotlinx.android.synthetic.main.item_course.view.*
//
//class CourseAdapter(
//    private val onCourseClickListener: (course: Course) -> Unit
//) : ListAdapter<Course, CourseAdapter.CourseViewHolder>(CourseDiffCallback) {
//
//     class CourseViewHolder( override val containerView: View, clickAtPosition: (Int) -> Unit) : RecyclerView.ViewHolder(containerView), LayoutContainer {
//
//        init {
//            containerView.setOnClickListener {
//                clickAtPosition(adapterPosition)
//            }
//        }
//        fun bind(course: Course) {
//            item_course_tv_title.text = course.courseName
//            item_course_tv_lecturer.text = course.courseLecturer.toString()
//            item_course_tv_semester.text = "A"
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder =
//        CourseViewHolder(
//            LayoutInflater.from(parent.context).
//                inflate(R.layout.item_course, parent, false)
//        ) {
//            onCourseClickListener(getItem(it))
//        }
//
//    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) =
//        holder.bind(getItem(position))
//}
//
///**
// * Using Diffutils to compare between two Courses.
// */
//object CourseDiffCallback : DiffUtil.ItemCallback<Course>() {
//    override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean = oldItem.courseId == newItem.courseId
//    override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean = oldItem == newItem
//}