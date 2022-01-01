package com.example.moodle.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moodle.R
import com.example.moodle.models.Course
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.holder_course.*
import kotlinx.android.synthetic.main.holder_course.view.*

class CourseAdapter(private val onCourseClickListener: (course: Course) -> Unit) : ListAdapter<Course, CourseAdapter.CourseViewHolder>(CourseDiffCallback()) {

    /**
     * @property itemView - current item in the recyclerview.
     * This class is responsible for binding the data for each row in the recyclerview.
     */
     class CourseViewHolder( override val containerView: View, clickAtPosition: (Int) -> Unit) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        init {
            containerView.setOnClickListener {
                clickAtPosition(adapterPosition)
            }
        }
        fun bind(course: Course) {
                holder_course_title.text = course.courseName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder =
        CourseViewHolder(
            LayoutInflater.from(parent.context).
                inflate(R.layout.holder_course, parent, false)
        ) {
            onCourseClickListener(getItem(it))
        }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) =
        holder.bind(getItem(position))
}

/**
 * Using Diffutils to compare between two Courses.
 */
class CourseDiffCallback : DiffUtil.ItemCallback<Course>() {
    override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean = oldItem.courseId == newItem.courseId

    override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean = oldItem == newItem

}