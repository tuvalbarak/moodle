package com.example.moodle.utils

import android.app.Dialog
import android.content.Context
import com.example.moodle.R


class ProgressBar {

    private var dialog: Dialog? = null

    fun show(context: Context?) {
        if (dialog == null || dialog?.isShowing == false) {
            context?.let {
                dialog = Dialog(context).apply {
                    setContentView(R.layout.progressbar)
                    window?.setBackgroundDrawableResource(android.R.color.transparent)
                    show()
                }
            }
        }
    }

    fun dismiss() {
        dialog?.apply {
            if (isShowing) {
                dismiss()
            }
        }
    }

    companion object {
        var instance: ProgressBar? = null

        fun instance() = instance ?: run {
            instance = ProgressBar()
            instance!!
        }
    }
}