package com.example.moodle.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moodle.R
import com.example.moodle.extensions.toDateDayMonthAndYear
import com.example.moodle.models.HomeAssignment
import kotlinx.android.synthetic.main.item_vertical_assignment.view.*

class VerticalAssignmentAdapter(
    private val onAssignmentClickListener: (assignment: HomeAssignment) -> Unit
) : ListAdapter<HomeAssignment, VerticalAssignmentViewHolder>(VerticalAssignmentDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalAssignmentViewHolder =
        VerticalAssignmentViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_vertical_assignment, parent, false),
            onAssignmentClickListener
        )

    override fun onBindViewHolder(holderHorizontal: VerticalAssignmentViewHolder, position: Int) {
        holderHorizontal.bind(getItem(position))
    }
}

class VerticalAssignmentViewHolder(
    itemView: View,
    private val onAssignmentClickListener: (assignment: HomeAssignment) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    fun bind(assignment: HomeAssignment) {
        itemView.apply {

            val dueDate = "Due Date: " + assignment.dueDate.toDateDayMonthAndYear()
            val grade = "Grade: " + if(assignment.isGraded == true) assignment.grade else "Not graded yet..."
            val feedback = "Feedback: " + if(assignment.isSubmitted == true) assignment.feedback else "No feedback yet..."

            item_vertical_assignment_due_date.text = dueDate
            item_vertical_assignment_grade.text = grade
            item_vertical_assignment_feedback.text = feedback
            item_vertical_assignment_name.text = assignment.name
            setBackgroundColor( if(assignment.isSubmitted == true) Color.GREEN else Color.RED)

            this.setOnClickListener {
                onAssignmentClickListener.invoke(assignment)
            }
        }
    }
}

/**
 * Using Diffutils to compare between two Courses.
 */
object VerticalAssignmentDiffCallback : DiffUtil.ItemCallback<HomeAssignment>() {
    override fun areItemsTheSame(oldItem: HomeAssignment, newItem: HomeAssignment): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: HomeAssignment, newItem: HomeAssignment): Boolean = oldItem == newItem
}