package com.example.moodle.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.moodle.R

class SingleAssignmentFragment : Fragment(R.layout.fragment_single_assignment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getSafeArgs()
    }

    private fun getSafeArgs() {
        arguments?.let {
            SingleAssignmentFragmentArgs.fromBundle(it).assignment?.apply {
                Log.d(TAG, name ?: "name is null")
            }
        }
    }

    companion object {
        const val TAG = "SingleAssignmentFragmentTAG"
    }

}