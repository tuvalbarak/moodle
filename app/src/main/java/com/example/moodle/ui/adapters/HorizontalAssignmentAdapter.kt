package com.example.moodle.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moodle.R
import com.example.moodle.extensions.toDateDayMonthAndYear
import com.example.moodle.models.HomeAssignment
import kotlinx.android.synthetic.main.item_horizontal_assignment.view.*


class HorizontalAssignmentAdapter(
    private val onAssignmentClickListener: (assignment: HomeAssignment) -> Unit
) : ListAdapter<HomeAssignment, HorizontalAssignmentViewHolder>(HorizontalAssignmentDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalAssignmentViewHolder =
        HorizontalAssignmentViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_horizontal_assignment, parent, false),
            onAssignmentClickListener
        )

    override fun onBindViewHolder(holderHorizontal: HorizontalAssignmentViewHolder, position: Int) {
        holderHorizontal.bind(getItem(position))
    }
}

class HorizontalAssignmentViewHolder(
    itemView: View,
    private val onAssignmentClickListener: (assignment: HomeAssignment) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    fun bind(assignment: HomeAssignment) {
        itemView.apply {
            item_horizontal_assignment_tv_name.text = assignment.name

            var grade = "Grade: "
            if(assignment.isGraded == true) {
                grade += if(assignment.grade == -1) "Not graded yet..." else assignment.grade
            } else {
                grade += "Not graded yet..."
            }

            item_horizontal_assignment_tv_grade.text = grade

            this.setOnClickListener {
                onAssignmentClickListener.invoke(assignment)
            }
        }
    }
}

/**
 * Using Diffutils to compare between two Courses.
 */
object HorizontalAssignmentDiffCallback : DiffUtil.ItemCallback<HomeAssignment>() {
    override fun areItemsTheSame(oldItem: HomeAssignment, newItem: HomeAssignment): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: HomeAssignment, newItem: HomeAssignment): Boolean = oldItem == newItem
}